package hunkarada.nen.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class NenGuiRenderer {
    // after experiments, I understood, that coordinate system is equal to monitor resolution / 4.
    // oh, no, it doesn't, it is because of gui scaling.
    public static void onHudRender(DrawContext drawContext, float v){
        renderNenBar(drawContext, v);
    }
    private static void renderNenBar(DrawContext drawContext, float v){
        MatrixStack matrixStack = drawContext.getMatrices();
        Matrix4f positionMatrix = drawContext.getMatrices().peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        int scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int scaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();
        IPlayerEntityNen playerEntityNen = (IPlayerEntityNen) MinecraftClient.getInstance().player;
        double value = playerEntityNen != null ? playerEntityNen.nen$getNenPower() / playerEntityNen.nen$getNenPowerCap() : 0f;
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
        buffer.vertex(positionMatrix, scaledWidth * 0.05f, scaledHeight * 0.85f, 0).color(1F, 1F, 1F, 1F).texture(0, 0).next();
        buffer.vertex(positionMatrix,scaledWidth * 0.05f, scaledHeight * 0.95f, 0).color(1F, 1F, 1F, 1F).texture(0, 1f).next();
        buffer.vertex(positionMatrix, (float) (scaledWidth * (0.05f + value * 0.2)), scaledHeight * 0.95f, 0).color(1F, 1F, 1F, 1F).texture((float) value, 1f).next();
        buffer.vertex(positionMatrix, (float) (scaledWidth * (0.05f + value * 0.2)),scaledHeight * 0.85f, 0).color(1F, 1F, 1F, 1F).texture((float) value, 0).next();
        RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
        RenderSystem.setShaderTexture(0, new Identifier(NenMod.MOD_ID, "nen_bar.png"));
        RenderSystem.setShaderColor(1, 1, 1, 1);
        tessellator.draw();
    }
}
