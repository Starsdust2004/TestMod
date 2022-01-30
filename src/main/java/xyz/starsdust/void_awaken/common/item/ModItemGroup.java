package xyz.starsdust.void_awaken.common.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ModItemGroup {
    public static final ItemGroup MOD_ITEM = new ItemGroup("voidAwakingItem") {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.VOID_TERMINAL);
        }
    };
}
