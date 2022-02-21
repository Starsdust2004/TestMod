package xyz.starsdust.void_awaken.client.gui.terminal.node;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class Require {
    private final ItemStack[] requireItems;
    private final int requireVoidpower;
    private final int requireExp;
    private final PlayerEntity user;

    public Require(PlayerEntity player, ItemStack[] requireItems, int requireVoidpower, int requireExp, String... playerAction) {
        this.requireItems = requireItems;
        this.requireVoidpower = requireVoidpower;
        this.requireExp = requireExp;
        this.user = player;
    }

    public boolean reachedItems() {
        if (this.requireItems.length != 0) {
            for (ItemStack item : this.requireItems) {
                if (!user.inventory.items.contains(item)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean reachedVoidpower() {
        if (this.requireVoidpower != 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean reachedExp() {
        if (this.requireExp != 0) {
            return this.user.experienceLevel >= this.requireExp;
        } else {
            return true;
        }
    }

    public boolean reachedPlayerAction() {
        return true;
    }

    public ItemStack[] getRequireItems() {
        return this.requireItems;
    }

    public int getRequireVoidpower() {
        return this.requireVoidpower;
    }

    public int getRequireExp() {
        return this.requireExp;
    }
}
