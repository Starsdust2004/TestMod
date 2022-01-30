package xyz.starsdust.void_awaken;

import net.minecraftforge.fml.common.Mod;
import xyz.starsdust.void_awaken.common.util.ModRegistry;

@Mod(VoidAwaken.MOD_ID)
public class VoidAwaken {
    public static final String MOD_ID = "void_awaken";

    public VoidAwaken() {
        ModRegistry.init();
    }
}
