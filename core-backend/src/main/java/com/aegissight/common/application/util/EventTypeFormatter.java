package com.aegissight.common.application.util;

import com.aegissight.common.domain.model.EventType;
import com.aegissight.common.domain.model.Severity;

public final class EventTypeFormatter {

    private EventTypeFormatter() {
    }

    public static String toTitle(EventType eventType) {
        return switch (eventType) {
            case INTRUSION -> "Intrusion detected";
            case PHYSICAL_DISTURBANCE -> "Physical disturbance detected";
            case CROWD_ANOMALY -> "Crowd anomaly detected";
            case FIRE_SMOKE -> "Fire or smoke detected";
            case PERSON_COLLAPSE -> "Person collapse detected";
            case WEAPON_DETECTED -> "Weapon detected";
            case VEHICLE_ANOMALY -> "Vehicle anomaly detected";
            case UNKNOWN -> "Unknown event detected";
        };
    }

    public static String toAlertTitle(Severity severity) {
        return switch (severity) {
            case CRITICAL -> "Critical incident detected";
            case HIGH -> "High severity incident detected";
            case MEDIUM -> "Medium severity incident detected";
            case LOW -> "Low severity incident detected";
        };
    }
}
