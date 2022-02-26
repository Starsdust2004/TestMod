package xyz.starsdust.void_awaken.client.gui.terminal.node;

import net.minecraft.util.ResourceLocation;
import xyz.starsdust.void_awaken.client.gui.terminal.node.page.Page;

import java.util.ArrayList;

public class NoRequireNode extends Node{
    public NoRequireNode(String nodeName, int pointX, int pointY, ResourceLocation icon, ArrayList<Page> pages, NodeType type) {
        this(nodeName, pointX, pointY, icon, pages, type, null);
    }

    public NoRequireNode(String nodeName, int pointX, int pointY, ResourceLocation icon, ArrayList<Page> pages, NodeType type, Node superiorNode) {
        super(nodeName, icon, pages, type, pointX, pointY, superiorNode);
    }
}
