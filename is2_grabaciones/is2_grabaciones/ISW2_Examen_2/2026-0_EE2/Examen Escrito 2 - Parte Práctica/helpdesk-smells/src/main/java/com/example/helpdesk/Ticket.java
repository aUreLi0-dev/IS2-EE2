package com.example.helpdesk;

import java.time.Instant;

/**
 * Simple data holder (intentionally primitive-heavy).
 */
public class Ticket {
    private final String id;
    private final String customerType; // "FREE", "PRO", "ENTERPRISE"
    private final String category;     // "BUG", "FEATURE", "BILLING"
    private final String severity;     // "LOW", "MEDIUM", "HIGH", "CRITICAL"
    private final String channel;      // "EMAIL", "CHAT", "PHONE"
    private final Instant createdAt;
    private String status;             // "OPEN", "IN_PROGRESS", "ESCALATED", "CLOSED"

    public Ticket(String id, String customerType, String category, String severity, String channel, Instant createdAt) {
        this.id = id;
        this.customerType = customerType;
        this.category = category;
        this.severity = severity;
        this.channel = channel;
        this.createdAt = createdAt;
        this.status = "OPEN";
    }

    public String getId() { return id; }
    public String getCustomerType() { return customerType; }
    public String getCategory() { return category; }
    public String getSeverity() { return severity; }
    public String getChannel() { return channel; }
    public Instant getCreatedAt() { return createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
