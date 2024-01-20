package hunkarada.nen.common.event;

import hunkarada.nen.common.network.ModMessages;
import hunkarada.nen.common.network.packet.SyncPacket;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class OnPlayerLogin implements ServerPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        SyncPacket.send(handler.player, ModMessages.SYNC_PACKET_ID, PacketByteBufs.create());
    }
}
