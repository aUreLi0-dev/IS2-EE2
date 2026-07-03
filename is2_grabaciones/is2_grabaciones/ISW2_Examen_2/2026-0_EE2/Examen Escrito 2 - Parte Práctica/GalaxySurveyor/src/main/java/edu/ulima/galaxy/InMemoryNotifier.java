package edu.ulima.galaxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryNotifier implements Notifier {

    

    private final List<Event> events = new ArrayList<>();

    @Override
    public void notifyUniversity(Quadrant quadrant, double probability) {
        events.add(new Event("UNIVERSITY", quadrant, probability));
    }

    @Override
    public void notifyNasa(Quadrant quadrant, double probability) {
        events.add(new Event("NASA", quadrant, probability));
    }

    public List<Event> events() {
        return Collections.unmodifiableList(events);
    }
}
