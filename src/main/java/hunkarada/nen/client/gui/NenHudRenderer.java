package hunkarada.nen.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class NenHudRenderer {
    // after experiments, I understood, that coordinate system is equal to monitor resolution / 4.
    // oh, no, it doesn't, it is because of gui scaling.
    public static void onHudRender(DrawContext drawContext){
        renderStaticHud(drawContext);
        renderNenBar(drawContext);
    }
    private static void renderStaticHud(DrawContext drawContext){
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        Matrix4f positionMatrix = drawContext.getMatrices().peek().getPositionMatrix();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();

        int scaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

        buffer.vertex(positionMatrix, 0, scaledHeight - 142, 0f).color(1f, 1f, 1f, 1f).texture(0f, 0f);
        buffer.vertex(positionMatrix, 0, scaledHeight, 0f).color(1f, 1f, 1f, 1f).texture(0f, 1f);
        buffer.vertex(positionMatrix, 122, scaledHeight, 0f).color(1f, 1f, 1f, 1f).texture(1f, 1f);
        buffer.vertex(positionMatrix, 122, scaledHeight - 142, 0f).color(1f, 1f, 1f, 1f).texture(1f, 0f);

        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderTexture(0, Identifier.of(NenMod.MOD_ID, "textures/nen/nen_hud.png"));
        RenderSystem.setShaderColor(1, 1, 1, 1);

        BufferRenderer.draw(buffer.end());
    }
    private static void renderNenBar(DrawContext drawContext){
        Matrix4f positionMatrix = drawContext.getMatrices().peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();

        int scaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

        IPlayerEntityNen playerEntityNen = (IPlayerEntityNen) MinecraftClient.getInstance().player;
        double value = playerEntityNen != null ? playerEntityNen.nen$getNenPower() / playerEntityNen.nen$getNenPowerCap() : 0f;


        buffer.vertex(positionMatrix, 3.1f, scaledHeight - 18.7f, 0).color(1F, 1F, 1F, 1F).texture(0f, 0f);
        buffer.vertex(positionMatrix,3.1f, scaledHeight - 3, 0).color(1F, 1F, 1F, 1F).texture(0f, 1f);
        buffer.vertex(positionMatrix, (float) ((value * 119)), scaledHeight - 3, 0).color(1F, 1F, 1F, 1F).texture((float) value, 1f);
        buffer.vertex(positionMatrix, (float) ((value * 119)),scaledHeight - 18.7f, 0).color(1F, 1F, 1F, 1F).texture((float) value, 0f);
        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderTexture(0, Identifier.of(NenMod.MOD_ID, "textures/nen/nen_bar.png"));
        RenderSystem.setShaderColor(1, 1, 1, 1);

        BufferRenderer.draw(buffer.end());
    }
}
