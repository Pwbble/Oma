package com.oma.miscellaneous;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemStackSerializer {

    /**
     * Serializes a list of {@link ItemStack}'s using Base64 for easy storage.
     *
     * @param items List of {@link ItemStack}'s to be serialized.
     * @return A serialized {@link ItemStack} list.
     */
    public String itemStackSerialization(ItemStack... items) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BukkitObjectOutputStream boos = new BukkitObjectOutputStream(baos);

            boos.writeInt(items.length);
            for (ItemStack item : items) boos.writeObject(item);
            boos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Deserializes a serialized {@link ItemStack} list using Base64.
     *
     * @param base64 Serialized string to be deserialized.
     * @return A deserialized {@link ItemStack} list.
     */
    public ItemStack[] itemStackDeserialization(String base64) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(base64));
            BukkitObjectInputStream bois = new BukkitObjectInputStream(bais);

            ItemStack[] items = new ItemStack[bois.readInt()];
            for (int i = 0; i < items.length; i++) items[i] = (ItemStack) bois.readObject();
            bois.close();
            return items;
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}