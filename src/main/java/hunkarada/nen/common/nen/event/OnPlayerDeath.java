package hunkarada.nen.common.nen.event;

import hunkarada.nen.common.nen.IEntityNen;
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
        // resetting some part of nen values after death on player part
        nenPlayerData.putDouble("nenPower", 0);
        nenPlayerData.putBoolean("isNenActive", false);
        nenPlayerData.putBoolean("isNenHidden", false);
        nenPlayerData.putBoolean("isCanSeeNen", false);
        nenPlayerData.putBoolean("isNenBlocked", false);
        nenPlayerData.putInt("nenBlockedTime", 0);

        newNenPlayer.nen$loadDataFromNbt(nenPlayerData);
        newNenPlayer.nen$updateAttributes();

        // same, but for entity part
        IEntityNen oldEntityPlayer = (IEntityNen) oldPlayer;
        IEntityNen newEntityPlayer = (IEntityNen) newPlayer;

        NbtCompound oldNenEntityData = oldEntityPlayer.nen$saveNenEntityToNbt();
        oldNenEntityData.put("nenAbilityEffects", new NbtCompound());
        oldNenEntityData.put("nenMemory", new NbtCompound());
        newEntityPlayer.nen$loadNenEntityFromNbt(oldNenEntityData);
    }
}
