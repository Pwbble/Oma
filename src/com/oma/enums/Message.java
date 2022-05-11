package com.oma.enums;

public enum Message {

    INVALID_PERMISSION("oma.miscellaneous.invalid_permission"),
    INVALID_TARGET("oma.miscellaneous.invalid_target"),
    NO_CONSOLE("oma.miscellaneous.no_console"),

    UPDATE_GMC("oma.gmc.gmc"),
    USAGE_GMC("oma.gmc.usage_gmc"),
    ALREADY_GMC("oma.gmc.already_gmc"),

    JOIN("oma.playerjoin.join"),
    ;

    Message(String configurationPath) { // Adds arguments to any enum in this class.
        this.path = configurationPath; // Sets (final String) path to configurationPath.
    }

    private final String path;

    public String getPath() {
        return path; // Returns (final String) path set as configurationPath.
    }
}