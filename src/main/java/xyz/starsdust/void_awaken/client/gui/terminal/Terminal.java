package xyz.starsdust.void_awaken.client.gui.terminal;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.starsdust.void_awaken.VoidAwaken;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class Terminal extends Screen {
    TextFieldWidget textFieldWidget;
    ResourceLocation OBSIDIAN_FIRST_GUI_TEXTURE = new ResourceLocation(VoidAwaken.MOD_ID, "assets/void_awaken/textures/gui/terminal_background.png");
    TranslationTextComponent content = new TranslationTextComponent("gui." + VoidAwaken.MOD_ID + ".first");

    protected Terminal(ITextComponent title) {
        super(title);
    }

    @Override
    protected void init() {
        assert this.minecraft != null;
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        this.textFieldWidget = new TextFieldWidget(this.font,
                this.width / 2 - 100,
                66,
                200,
                20,
                new TranslationTextComponent("gui." + VoidAwaken.MOD_ID + ".terminal"));
        this.children.add(this.textFieldWidget);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(OBSIDIAN_FIRST_GUI_TEXTURE);
        int textureWidth = 208;
        int textureHeight = 156;
        blit(matrixStack, this.width / 2 - 150, 10, 0, 0, 300, 200, textureWidth, textureHeight);
        drawCenteredString(matrixStack, this.font, content, this.width / 2 - 10, 30, 0xeb0505);
        this.textFieldWidget.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
