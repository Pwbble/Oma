package com.oma.handlers;

public class InventoryHandler {

    private static InventoryHandler Instance;

    public static InventoryHandler createInstance() {
        return Instance;
    }
}