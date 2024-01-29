package hunkarada.nen.common.network.packet;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class SyncPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        client.executeTask(() -> {
            IPlayerEntityNen nenPlayer = null;
            try {
                nenPlayer = (IPlayerEntityNen) Objects.requireNonNull(client.player);
            } catch (NullPointerException ignore) {
            }
            if (nenPlayer != null) {
                nenPlayer.nen$loadDataFromNbt(buf.readNbt());
            }
        });
    }
    public static void send(ServerPlayerEntity player, Identifier channelName, PacketByteBuf buf) {
        IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
        buf.writeNbt(nenPlayer.nen$saveDataToNbt());
        ServerPlayNetworking.send(player, channelName, buf);
    }
}