package xyz.starsdust.void_awaken.client.gui.terminal.node;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.starsdust.void_awaken.VoidAwaken;
import xyz.starsdust.void_awaken.client.gui.terminal.ScreenTerminal;
import xyz.starsdust.void_awaken.client.gui.terminal.node.page.Page;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;
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
        this.nodeName = new TranslationTextComponent(VoidAwaken.MOD_ID, nodeName);
        this.icon = icon;
        this.pages = pages;
        this.nodeType = type;
        this.pointX = pointX;
        this.pointY = pointY;
        this.superiorNode = superiorNode;
    }

    public Button getNodeButton(ScreenTerminal terminal) {
        return new NodeButton(terminal, this);
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
        return new Point(this.pointX * 60, this.pointY * 60);
    }

    public Node getSuperiorNode() {
        return superiorNode;
    }

    public boolean isCompletion() {
        return isCompletion;
    }

    class NodeButton extends Button{
        ScreenTerminal terminal;
        Node node;

        public NodeButton(ScreenTerminal terminal, Node node) {
            super(pointX * 60 + terminal.datumPointX, pointY * 60 + terminal.datumPointY, 30, 30, StringTextComponent.EMPTY, (button) -> {});
            this.terminal = terminal;
            this.node = node;
        }

        @Override
        @ParametersAreNonnullByDefault
        public void render(MatrixStack matrixStack, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
            this.x = (int) (140 + pointX * 60 + terminal.datumPointX + terminal.pageOffsetX - terminal.zoomLevel/2 + pointX * terminal.zoomLevel * 2);
            this.y = (int) (90 + pointY * 60 + terminal.datumPointY + terminal.pageOffsetY - terminal.zoomLevel/2 + pointY * terminal.zoomLevel * 2);
            this.width = 30 + terminal.zoomLevel;
            this.height = 30 + terminal.zoomLevel;
            super.render(matrixStack, p_230431_2_, p_230431_3_, p_230431_4_);
        }

        @Override
        @ParametersAreNonnullByDefault
        public void renderButton(MatrixStack matrixStack, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
            super.renderButton(matrixStack, p_230431_2_, p_230431_3_, p_230431_4_);
        }

        @Override
        @ParametersAreNonnullByDefault
        public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
            this.terminal.renderTooltip(matrixStack, this.node.getNodeName(), mouseX, mouseY);
        }
    }
}
