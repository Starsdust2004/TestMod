package xyz.starsdust.void_awaken.client.gui.terminal;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;
import xyz.starsdust.void_awaken.VoidAwaken;
import xyz.starsdust.void_awaken.client.gui.terminal.node.Nodes;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class ScreenTerminal extends Screen {
    private static ScreenTerminal terminal;
    private final ResourceLocation TERMINAL = new ResourceLocation(VoidAwaken.MOD_ID, "textures/gui/terminal.png");
    private final ResourceLocation TERMINAL_BACKGROUND = new ResourceLocation(VoidAwaken.MOD_ID, "textures/gui/terminal_background.png");
    private double xScale;
    private double yScale;
    public int datumPointX;
    public int datumPointY;
    public double pageOffsetX = 0;
    public double pageOffsetY = 0;
    //这里限制手册最多可以往上下左右拖多远
    //这个方向不是偏移值方向
    public int[] maxPageOffsetRange = {100, 100, 100, 100};
    public int zoomLevel = 0;

    private ScreenTerminal() {
        super(new TranslationTextComponent("item.void_awaken.void_terminal"));
    }

    @Override
    protected void init() {
        this.xScale = (double) this.getMinecraft().getWindow().getWidth() / (double) this.getMinecraft().getWindow().getGuiScaledWidth();
        this.yScale = (double) this.getMinecraft().getWindow().getWidth() / (double) this.getMinecraft().getWindow().getGuiScaledWidth();
        this.datumPointX = this.width / 2 - 150;
        this.datumPointY = this.height / 2 - 100;
        this.getMinecraft().keyboardHandler.setSendRepeatsToGui(true);
        this.addButton(Nodes.TEST_NODE.getNodeButton(this));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) (this.datumPointX * this.xScale), (int) (this.datumPointY * this.yScale), (int) (300 * this.xScale), (int) (200 * this.yScale));
        renderTerminalBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        this.getMinecraft().getTextureManager().bind(TERMINAL);
        blit(matrixStack, this.width / 2 - 158, this.height / 2 - 104, 0, 0, 316, 210, 316, 210);
    }

    public void renderTerminalBackground(MatrixStack matrixStack) {
        this.getMinecraft().getTextureManager().bind(TERMINAL_BACKGROUND);
        int textureWH = 128 + zoomLevel;
        blit(matrixStack, (int) (-1000 + this.pageOffsetX - this.zoomLevel * 20), (int) (-1000 + this.pageOffsetY - this.zoomLevel * 18), 0, 0, 3000, 2000, textureWH, textureWH);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double mouseMovementDistanceX, double mouseMovementDistanceY) {
        if (mouseX > (float) this.width / 2 - 158 && mouseX < (float) this.width / 2 + 158 && mouseY > (float) this.height / 2 - 104 && mouseY < (float) this.height / 2 + 104) {
            double targetX = this.pageOffsetX + mouseMovementDistanceX;
            double targetY = this.pageOffsetY + mouseMovementDistanceY;
            if (targetX <= this.maxPageOffsetRange[2] && targetX >= -this.maxPageOffsetRange[3]) {
                this.pageOffsetX = targetX;
            }
            if (targetY <= this.maxPageOffsetRange[0] && targetY >= -this.maxPageOffsetRange[1]) {
                this.pageOffsetY = targetY;
            }
        }
        return super.mouseDragged(mouseX, mouseY, mouseButton, mouseMovementDistanceX, mouseMovementDistanceY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
        int newZoomLevel = zoomLevel + (int) scroll;
        if (newZoomLevel <= 10 && newZoomLevel >= -10) {
            this.zoomLevel = newZoomLevel;
        }
        return super.mouseScrolled(mouseX, mouseY, scroll);
    }

    @Override
    public void onClose() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        super.onClose();
    }

    public static ScreenTerminal getScreenTerminal() {
        if (terminal == null){
            terminal = new ScreenTerminal();
        }
        return terminal;
    }
}
