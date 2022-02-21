package xyz.starsdust.void_awaken.client.gui.terminal.node;

import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class Nodes {
    public static ArrayList<Node> allNodes = new ArrayList<>();
    public static final Node TEST_NODE = new NodeTextbook("item.void_awaken.void_terminal", 0, 0, new ResourceLocation("textures/item/paper.png"), null, NodeType.BASIC_TEXTBOOK);
}
