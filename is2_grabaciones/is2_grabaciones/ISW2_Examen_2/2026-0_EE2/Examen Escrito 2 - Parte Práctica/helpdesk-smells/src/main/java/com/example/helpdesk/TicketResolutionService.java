package com.example.helpdesk;

import java.time.Duration;
import java.time.Instant;
import java.util.Locale;


public class TicketResolutionService {

    private double usdToPenRate = 3.75; // PEN per USD (should be configurable, value object, etc.)

    public TicketOutcome processTicket(Ticket t, String agentId, InMemoryNotifier notifier) {
        if (t == null) throw new IllegalArgumentException("ticket is null");
        if (agentId == null || agentId.isBlank()) throw new IllegalArgumentException("agentId is blank");
        if (notifier == null) throw new IllegalArgumentException("notifier is null");

        String customer = t.getCustomerType().trim().toUpperCase(Locale.ROOT);
        String category = t.getCategory().trim().toUpperCase(Locale.ROOT);
        String severity = t.getSeverity().trim().toUpperCase(Locale.ROOT);
        String channel  = t.getChannel().trim().toUpperCase(Locale.ROOT);

        double baseUsd = 0.0;
        if (customer.equals("FREE")) {
            if (category.equals("BUG")) baseUsd = 5.0;
            else if (category.equals("FEATURE")) baseUsd = 8.0;
            else if (category.equals("BILLING")) baseUsd = 6.0;
            else baseUsd = 7.0;

            if (severity.equals("LOW")) baseUsd += 1.0;
            else if (severity.equals("MEDIUM")) baseUsd += 3.0;
            else if (severity.equals("HIGH")) baseUsd += 6.0;
            else if (severity.equals("CRITICAL")) baseUsd += 10.0;
            else baseUsd += 2.0;

        } else if (customer.equals("PRO")) {
            if (category.equals("BUG")) baseUsd = 12.0;
            else if (category.equals("FEATURE")) baseUsd = 15.0;
            else if (category.equals("BILLING")) baseUsd = 10.0;
            else baseUsd = 11.0;

            if (severity.equals("LOW")) baseUsd += 2.0;
            else if (severity.equals("MEDIUM")) baseUsd += 4.0;
            else if (severity.equals("HIGH")) baseUsd += 8.0;
            else if (severity.equals("CRITICAL")) baseUsd += 15.0;
            else baseUsd += 3.0;

        } else if (customer.equals("ENTERPRISE")) {
            if (category.equals("BUG")) baseUsd = 25.0;
            else if (category.equals("FEATURE")) baseUsd = 30.0;
            else if (category.equals("BILLING")) baseUsd = 20.0;
            else baseUsd = 22.0;

            if (severity.equals("LOW")) baseUsd += 3.0;
            else if (severity.equals("MEDIUM")) baseUsd += 6.0;
            else if (severity.equals("HIGH")) baseUsd += 12.0;
            else if (severity.equals("CRITICAL")) baseUsd += 25.0;
            else baseUsd += 4.0;

        } else {
            baseUsd = 9.0;
        }

        if (channel.equals("PHONE")) baseUsd += 4.0;
        else if (channel.equals("CHAT")) baseUsd += 1.5;
        else if (channel.equals("EMAIL")) baseUsd += 0.0;
        else baseUsd += 0.5;

        long targetMinutes;
        if (customer.equals("ENTERPRISE")) {
            if (severity.equals("CRITICAL")) targetMinutes = 30;
            else if (severity.equals("HIGH")) targetMinutes = 60;
            else targetMinutes = 180;
        } else if (customer.equals("PRO")) {
            if (severity.equals("CRITICAL")) targetMinutes = 60;
            else if (severity.equals("HIGH")) targetMinutes = 180;
            else targetMinutes = 360;
        } else {
            if (severity.equals("CRITICAL")) targetMinutes = 240;
            else if (severity.equals("HIGH")) targetMinutes = 720;
            else targetMinutes = 1440;
        }

        long ageMinutes = Duration.between(t.getCreatedAt(), Instant.now()).toMinutes();
        boolean breach = ageMinutes > targetMinutes;

        if (breach) {
            t.setStatus("ESCALATED");
            notifier.notify("SLACK:#helpdesk-escalations", "Ticket " + t.getId() + " breached SLA. Agent=" + agentId);
            if (severity.equals("CRITICAL")) {
                notifier.notify("SMS:ONCALL", "CRITICAL ticket " + t.getId() + " breached SLA!");
            }
        } else {
            t.setStatus("IN_PROGRESS");
            if (customer.equals("ENTERPRISE")) {
                notifier.notify("EMAIL:account-manager", "Enterprise ticket " + t.getId() + " accepted by " + agentId);
            }
        }

        double totalPen = baseUsd * usdToPenRate;

        return new TicketOutcome(t.getId(), baseUsd, totalPen, targetMinutes, breach, t.getStatus());
    }

    public void setUsdToPenRate(double usdToPenRate) {
        this.usdToPenRate = usdToPenRate;
    }
}
