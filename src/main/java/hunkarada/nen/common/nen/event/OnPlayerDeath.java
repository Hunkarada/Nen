package hunkarada.nen.common.nen.event;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnPlayerDeath implements ServerPlayerEvents.CopyFrom{
    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        IPlayerEntityNen oldNenPlayer = (IPlayerEntityNen) oldPlayer;
        IPlayerEntityNen newNenPlayer = (IPlayerEntityNen) newPlayer;
        NbtCompound nenPlayerData = oldNenPlayer.nen$saveDataToNbt();
        // setting nen to zero.
        nenPlayerData.putDouble("nenPower", 0);

        newNenPlayer.nen$loadDataFromNbt(nenPlayerData);
    }
}
