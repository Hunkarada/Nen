package hunkarada.nen.common.network.packet;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EntityType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class AwakeNenPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        if (    player.getStatHandler().getStat(Stats.KILLED.getOrCreateStat(EntityType.WITHER)) >= 1
        &&      player.getStatHandler().getStat(Stats.CRAFTED.getOrCreateStat(Registries.ITEM.get(Identifier.of("minecraft", "beacon")))) >=1
        &&      player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.SPRINT_ONE_CM)) >= 1000000
        &&      player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.DAMAGE_DEALT)) >= 1000
        &&      player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.DAMAGE_TAKEN)) >= 100
        &&      !player.isDisconnected())
        {
            IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
            nenPlayer.nen$awakePlayer();
        }
    }
}
