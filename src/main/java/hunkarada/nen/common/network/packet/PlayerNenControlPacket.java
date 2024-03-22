package hunkarada.nen.common.network.packet;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import hunkarada.nen.common.network.ModMessages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerNenControlPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        server.execute(() -> {
            switch (buf.readInt()) {
                // activate nen
                case 0 -> {
                    IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
                    nenPlayer.nen$activateNenSwitch();
                }
                case 1 -> {
                // hide nen
                    IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
                    nenPlayer.nen$hideNenSwitch();
                }
                // can see nen
                case 2 -> {
                    IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
                    nenPlayer.nen$canSeeNenSwitch();
                }
            }
        });
    }
    public static void send(int index) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(index);
        ClientPlayNetworking.send(ModMessages.PLAYER_NEN_CONTROL_PACKET, buf);
    }
}
