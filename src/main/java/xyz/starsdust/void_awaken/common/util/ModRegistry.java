package xyz.starsdust.void_awaken.common.util;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.starsdust.void_awaken.common.item.ModItems;

public class ModRegistry {
    @SubscribeEvent
    public static void registryItem(RegistryEvent.Register<Item> register) {
        final IForgeRegistry<Item> r = register.getRegistry();
        for (Item item : ModItems.ALL_ITEM) {
            r.register(item);
        }
        ModItems.ALL_ITEM = null;
    }

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().register(ModRegistry.class);
    }
}
