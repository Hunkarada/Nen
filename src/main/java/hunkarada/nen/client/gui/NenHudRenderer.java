package hunkarada.nen.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class NenHudRenderer {
    // after experiments, I understood, that coordinate system is equal to monitor resolution / 4.
    // oh, no, it doesn't, it is because of gui scaling.
    public static void onHudRender(DrawContext drawContext, float v){
        renderStaticHud(drawContext, v);
        renderNenBar(drawContext, v);
    }
    private static void renderStaticHud(DrawContext drawContext, float v){
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        Matrix4f positionMatrix = drawContext.getMatrices().peek().getPositionMatrix();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();

        int scaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
        buffer.vertex(positionMatrix, 0, scaledHeight - 120, 0f).color(1f, 1f, 1f, 1f).texture(0f, 0f).next();
        buffer.vertex(positionMatrix, 0, scaledHeight, 0f).color(1f, 1f, 1f, 1f).texture(0f, 1f).next();
        buffer.vertex(positionMatrix, 120, scaledHeight, 0f).color(1f, 1f, 1f, 1f).texture(1f, 1f).next();
        buffer.vertex(positionMatrix, 120, scaledHeight - 120, 0f).color(1f, 1f, 1f, 1f).texture(1f, 0f).next();

        RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
        RenderSystem.setShaderTexture(0, new Identifier(NenMod.MOD_ID, "nen_hud.png"));
        RenderSystem.setShaderColor(1, 1, 1, 1);

        tessellator.draw();
    }
    private static void renderNenBar(DrawContext drawContext, float v){
        Matrix4f positionMatrix = drawContext.getMatrices().peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();

        int scaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

        IPlayerEntityNen playerEntityNen = (IPlayerEntityNen) MinecraftClient.getInstance().player;
        double value = playerEntityNen != null ? playerEntityNen.nen$getNenPower() / playerEntityNen.nen$getNenPowerCap() : 0f;

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
        buffer.vertex(positionMatrix, 3.1f, scaledHeight - 18.7f, 0).color(1F, 1F, 1F, 1F).texture(0f, 0f).next();
        buffer.vertex(positionMatrix,3.1f, scaledHeight - 3, 0).color(1F, 1F, 1F, 1F).texture(0f, 1f).next();
        buffer.vertex(positionMatrix, (float) ((value * 117)), scaledHeight - 3, 0).color(1F, 1F, 1F, 1F).texture((float) value, 1f).next();
        buffer.vertex(positionMatrix, (float) ((value * 117)),scaledHeight - 18.7f, 0).color(1F, 1F, 1F, 1F).texture((float) value, 0f).next();
        RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
        RenderSystem.setShaderTexture(0, new Identifier(NenMod.MOD_ID, "nen_bar.png"));
        RenderSystem.setShaderColor(1, 1, 1, 1);

        tessellator.draw();
    }
}
