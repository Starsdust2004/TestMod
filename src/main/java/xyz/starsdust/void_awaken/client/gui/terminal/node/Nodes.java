package xyz.starsdust.void_awaken.client.gui.terminal.node;

import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class Nodes {
    private final ArrayList<Node> nodes = new ArrayList<>();

    public Nodes() {
         appendNodes(new NoRequireNode("item.void_awaken.void_terminal", 0, 0, new ResourceLocation("textures/item/paper.png"), null, NodeType.BASIC_TEXTBOOK));
         appendNodes(new NoRequireNode("item.void_awaken.void_terminal", 1, 0, new ResourceLocation("textures/item/paper.png"), null, NodeType.BASIC_TEXTBOOK));
    }

    public Node getNodesAt(int index) {
        return nodes.get(index);
    }

    public void appendNodes(Node node) {
        this.nodes.add(node);
    }

    public int getLength() {
        return this.nodes.size();
    }
}
