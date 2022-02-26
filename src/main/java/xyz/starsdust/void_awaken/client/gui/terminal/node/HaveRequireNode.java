package xyz.starsdust.void_awaken.client.gui.terminal.node;

import net.minecraft.util.ResourceLocation;
import xyz.starsdust.void_awaken.client.gui.terminal.node.page.Page;

import java.util.ArrayList;

public class HaveRequireNode extends Node {
    protected Require nodeRequire;

    public HaveRequireNode(String nodeName, int pointX, int pointY, ResourceLocation icon, ArrayList<Page> pages, NodeType type, Require nodeRequire) {
        this(nodeName, pointX, pointY, icon, pages, type, nodeRequire, null);
    }

    public HaveRequireNode(String nodeName, int pointX, int pointY, ResourceLocation icon, ArrayList<Page> pages, NodeType type, Require nodeRequire, Node superiorNode) {
        super(nodeName, icon, pages, type, pointX, pointY, superiorNode);
        this.nodeRequire = nodeRequire;
    }

    public Require getNodeRequire() {
        return nodeRequire;
    }
}
