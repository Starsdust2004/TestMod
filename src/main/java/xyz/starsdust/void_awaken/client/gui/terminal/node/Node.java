package xyz.starsdust.void_awaken.client.gui.terminal.node;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.starsdust.void_awaken.client.gui.terminal.ScreenTerminal;
import xyz.starsdust.void_awaken.client.gui.terminal.node.page.Page;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Node {
    protected ITextComponent nodeName;
    protected ResourceLocation icon;
    protected ArrayList<Page> pages;
    protected NodeType nodeType;
    protected int pointX;
    protected int pointY;
    protected Node superiorNode;
    protected boolean isCompletion;

    public Node(String nodeName, ResourceLocation icon, ArrayList<Page> pages, NodeType type, int pointX, int pointY, Node superiorNode) {
        this.nodeName = new TranslationTextComponent(nodeName);
        this.icon = icon;
        this.pages = pages;
        this.nodeType = type;
        this.pointX = pointX * 30;
        this.pointY = pointY * 30;
        this.superiorNode = superiorNode;
    }

    public Button getNodeButton(ScreenTerminal terminal) {
        return new NodeButton(terminal);
    }

    public void setNodeName(ITextComponent newNodeName) {
        this.nodeName = newNodeName;
    }

    public ITextComponent getNodeName() {
        return nodeName;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public Point getNodePosition() {
        return new Point(this.pointX, this.pointY);
    }

    public Node getSuperiorNode() {
        return superiorNode;
    }

    public boolean isCompletion() {
        return isCompletion;
    }

    class NodeButton extends Button {
        ScreenTerminal terminal;

        public NodeButton(ScreenTerminal terminal) {
            super(pointX + terminal.datumPointX, pointY + terminal.datumPointY, 30, 30, new StringTextComponent("测试按钮"), (button) -> {});
            this.terminal = terminal;
        }

        @Override
        @ParametersAreNonnullByDefault
        public void render(MatrixStack p_230431_1_, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
            this.x = (int) (140 + pointX + terminal.datumPointX + terminal.pageOffsetX);
            this.y = (int) (90 + pointY + terminal.datumPointY + terminal.pageOffsetY);
            this.width = 30 + terminal.zoomLevel;
            this.height = 30 + terminal.zoomLevel;
            super.render(p_230431_1_, p_230431_2_, p_230431_3_, p_230431_4_);
        }
    }
}
