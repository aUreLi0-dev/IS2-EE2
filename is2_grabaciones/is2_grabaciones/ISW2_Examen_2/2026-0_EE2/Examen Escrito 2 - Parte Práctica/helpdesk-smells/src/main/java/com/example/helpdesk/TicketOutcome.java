package com.example.helpdesk;

public class TicketOutcome {
    private final String ticketId;
    private final double priceUsd;
    private final double pricePen;
    private final long slaTargetMinutes;
    private final boolean slaBreached;
    private final String finalStatus;

    public TicketOutcome(String ticketId, double priceUsd, double pricePen, long slaTargetMinutes, boolean slaBreached, String finalStatus) {
        this.ticketId = ticketId;
        this.priceUsd = priceUsd;
        this.pricePen = pricePen;
        this.slaTargetMinutes = slaTargetMinutes;
        this.slaBreached = slaBreached;
        this.finalStatus = finalStatus;
    }

    public String getTicketId() { return ticketId; }
    public double getPriceUsd() { return priceUsd; }
    public double getPricePen() { return pricePen; }
    public long getSlaTargetMinutes() { return slaTargetMinutes; }
    public boolean isSlaBreached() { return slaBreached; }
    public String getFinalStatus() { return finalStatus; }
}
