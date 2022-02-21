package xyz.starsdust.void_awaken.client.gui.terminal.node;

import net.minecraft.util.ResourceLocation;
import xyz.starsdust.void_awaken.client.gui.terminal.node.page.Page;

import java.util.ArrayList;

public class NodeTextbook extends Node {
    protected Require nodeRequire;

    public NodeTextbook(String nodeName, int pointX, int pointY, ResourceLocation icon, ArrayList<Page> pages, NodeType type) {
        this(nodeName, pointX, pointY, icon, pages, type, null, null);
    }

    public NodeTextbook(String nodeName,  int pointX, int pointY, ResourceLocation icon, ArrayList<Page> pages, NodeType type, Node superiorNode, Require nodeRequire) {
        super(nodeName, icon, pages, type, pointX, pointY, superiorNode);
        this.nodeRequire = nodeRequire;
    }

    public Require getNodeRequire() {
        return nodeRequire;
    }
}
