package hunkarada.nen.common.event;

import hunkarada.nen.common.network.ModMessages;
import hunkarada.nen.common.network.packet.SyncPacket;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnEndServerTick implements ServerTickEvents.EndTick {
    @Override
    public void onEndTick(MinecraftServer server) {
        for (ServerPlayerEntity serverPlayer : server.getPlayerManager().getPlayerList()){
            SyncPacket.send(serverPlayer, ModMessages.SYNC_PACKET_ID, PacketByteBufs.create());
        }
    }
}
