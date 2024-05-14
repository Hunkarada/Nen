package hunkarada.nen.common.network.packet;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public record AwakeNenPacket() implements CustomPayload {
    public static final CustomPayload.Id<AwakeNenPacket> ID = new Id<>(new Identifier(NenMod.MOD_ID, "awake_nen_packet"));
    public static final PacketCodec<RegistryByteBuf, AwakeNenPacket> CODEC = CustomPayload.codecOf(AwakeNenPacket::write, AwakeNenPacket::new);

    public AwakeNenPacket(RegistryByteBuf buf){
       this();
    }
    public void write(RegistryByteBuf buf){

    }
    public static void receive(AwakeNenPacket payload, ServerPlayNetworking.Context context){
        context.player().server.execute(() -> {
            if (context.player().getStatHandler().getStat(Stats.KILLED.getOrCreateStat(EntityType.WITHER)) >= 1
                    && context.player().getStatHandler().getStat(Stats.CRAFTED.getOrCreateStat(Registries.ITEM.get(Identifier.of("minecraft", "beacon")))) >= 1
                    && context.player().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.SPRINT_ONE_CM)) >= 1000000
                    && context.player().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.DAMAGE_DEALT)) >= 1000
                    && context.player().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.DAMAGE_TAKEN)) >= 100
                    && !context.player().isDisconnected()) {
                IPlayerEntityNen nenPlayer = (IPlayerEntityNen) context.player();
                nenPlayer.nen$awakePlayer();
            }
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
