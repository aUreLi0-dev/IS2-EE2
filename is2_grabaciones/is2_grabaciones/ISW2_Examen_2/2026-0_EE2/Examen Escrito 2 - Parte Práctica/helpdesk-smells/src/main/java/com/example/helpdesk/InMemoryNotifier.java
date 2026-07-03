package com.example.helpdesk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryNotifier {
    private final List<String> messages = new ArrayList<>();

    public void notify(String destination, String message) {
        messages.add(destination + "|" + message);
    }

    public List<String> messages() {
        return Collections.unmodifiableList(messages);
    }
}
