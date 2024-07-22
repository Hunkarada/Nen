package hunkarada.nen.common.networking.event;

import hunkarada.nen.common.networking.packets.SyncPacket;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnEndServerTick implements ServerTickEvents.EndTick {
    @Override
    public void onEndTick(MinecraftServer server) {
        for (ServerPlayerEntity serverPlayer : server.getPlayerManager().getPlayerList()){
            SyncPacket.send(serverPlayer);
        }
    }
}
