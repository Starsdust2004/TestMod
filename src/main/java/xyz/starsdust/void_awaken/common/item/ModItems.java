package xyz.starsdust.void_awaken.common.item;

import net.minecraft.item.Item;

import java.util.ArrayList;

public class ModItems {
    public static ArrayList<Item> ALL_ITEM = new ArrayList<>();

    public static final Item VOID_TERMINAL = registerItem("void_terminal", new ItemVoidTerminal());

    private static Item registerItem(String name, Item item) {
        item.setRegistryName(name);
        ALL_ITEM.add(item);
        return item;
    }

}
