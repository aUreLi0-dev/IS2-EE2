package com.example.helpdesk;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.Test;


public class TicketResolutionServiceTest {

    @Test
    public void enterpriseCriticalBreachesAndNotifies() {
        Ticket t = new Ticket("T-1", "ENTERPRISE", "BUG", "CRITICAL", "PHONE",
                Instant.now().minusSeconds(60 * 60)); // 60 min ago
        InMemoryNotifier notifier = new InMemoryNotifier();

        TicketResolutionService svc = new TicketResolutionService();
        svc.setUsdToPenRate(4.0);

        TicketOutcome out = svc.processTicket(t, "agent-99", notifier);

        assertTrue(out.isSlaBreached());
        assertEquals("ESCALATED", out.getFinalStatus());
        assertEquals(30, out.getSlaTargetMinutes());
        assertTrue(notifier.messages().stream().anyMatch(m -> m.contains("#helpdesk-escalations")));
        assertTrue(notifier.messages().stream().anyMatch(m -> m.contains("SMS:ONCALL")));
        assertTrue(out.getPricePen() > 0);
    }

    @Test
    public void freeLowEmailDoesNotBreach() {
        Ticket t = new Ticket("T-2", "free", "feature", "low", "email",
                Instant.now().minusSeconds(10 * 60)); // 10 min ago
        InMemoryNotifier notifier = new InMemoryNotifier();

        TicketResolutionService svc = new TicketResolutionService();
        svc.setUsdToPenRate(3.5);

        TicketOutcome out = svc.processTicket(t, "agent-01", notifier);

        assertFalse(out.isSlaBreached());
        assertEquals("IN_PROGRESS", out.getFinalStatus());
        assertEquals(1440, out.getSlaTargetMinutes());
        assertTrue(notifier.messages().isEmpty()); // free + low shouldn't notify
        assertEquals(out.getPriceUsd() * 3.5, out.getPricePen(), 0.0001);
    }
}
