package com.ijcsj.service_library.bean;

import java.io.IOException;

public enum MenuShowType {
    COMMON, FULL, SIMPLIFIED;

    public String toValue() {
        switch (this) {
            case COMMON: return "COMMON";
            case FULL: return "FULL";
            case SIMPLIFIED: return "SIMPLIFIED";
        }
        return null;
    }

    public static MenuShowType forValue(String value) throws IOException {
        if (value.equals("COMMON")) return COMMON;
        if (value.equals("FULL")) return FULL;
        if (value.equals("SIMPLIFIED")) return SIMPLIFIED;
        throw new IOException("Cannot deserialize MenuShowType");
    }
}