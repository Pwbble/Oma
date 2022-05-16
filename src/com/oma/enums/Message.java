package com.oma.enums;

public enum Message {

    INVALID_PERMISSION("oma.miscellaneous.invalid_permission"),
    INVALID_TARGET("oma.miscellaneous.invalid_target"),
    NO_CONSOLE("oma.miscellaneous.no_console"),

    DISABLE_FLY("oma.fly.disable_fly"),
    ENABLE_FLY("oma.fly.enable_fly"),
    USAGE_FLY("oma.fly.usage_fly"),

    ALREADY_GAMEMODE("oma.gamemode.already_gamemode"),
    TARGET_ALREADY_GAMEMODE("oma.gamemode.target_already_gamemode"),
    TARGET_UPDATE_GAMEMODE("oma.gamemode.target_update_gamemode"),
    UPDATE_GAMEMODE("oma.gamemode.update_gamemode"),
    USAGE_GAMEMODE("oma.gamemode.usage_gamemode"),

    WORLDS("oma.worlds.worlds"),

    JOIN("oma.playerjoin.join"),
    ;

    Message(String configurationPath) { // Adds arguments to any enum in this class.
        this.path = configurationPath; // Sets (final String) path to configurationPath.
    }

    private final String path;

    public String getPath() {
        return path; // Returns a (final String) path declared as configurationPath.
    }
}