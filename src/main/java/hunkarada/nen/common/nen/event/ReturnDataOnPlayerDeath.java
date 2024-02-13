package hunkarada.nen.common.nen.event;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class ReturnDataOnPlayerDeath implements ServerPlayerEvents.CopyFrom{
    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        IPlayerEntityNen oldNenPlayer = (IPlayerEntityNen) oldPlayer;
        IPlayerEntityNen newNenPlayer = (IPlayerEntityNen) newPlayer;
        newNenPlayer.nen$loadDataFromNbt(oldNenPlayer.nen$saveDataToNbt());
    }
}
