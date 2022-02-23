package xyz.starsdust.void_awaken.common.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.starsdust.void_awaken.client.gui.terminal.ScreenTerminal;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemVoidTerminal extends Item {
    public ItemVoidTerminal() {
        super(new Properties().tab(ModItemGroup.MOD_ITEM));
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (world.isClientSide) {
            Minecraft.getInstance().setScreen(ScreenTerminal.getScreenTerminal());
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return ActionResult.sidedSuccess(itemstack, world.isClientSide());
    }
}
