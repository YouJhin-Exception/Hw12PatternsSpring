package org.youjhin.hw12patternsspring.enums;

public enum Urgency {
    URGENT("Urgent"),
    REGULAR("Regular");

    private final String displayName;

    Urgency(String displayName) {
        this.displayName = displayName;
    }
}
