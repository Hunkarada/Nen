package hunkarada.nen.common.networking.packets;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Objects;

public record SyncPacket(NbtCompound nbtPlayerData) implements CustomPayload {
    public static final CustomPayload.Id<SyncPacket> ID = new Id<>(Identifier.of(NenMod.MOD_ID, "sync_packet"));
    public static final PacketCodec<RegistryByteBuf, SyncPacket> CODEC = PacketCodec.of(SyncPacket::write, SyncPacket::new).cast();
//    public static final PacketCodec<RegistryByteBuf, SyncPacket> CODEC = PacketCodec.tuple(PacketCodecs.NBT_COMPOUND, SyncPacket::nbtPlayerData, SyncPacket::new).cast();
    public SyncPacket(RegistryByteBuf buf){
        this(buf.readNbt());
    }
    public void write(RegistryByteBuf buf){
        buf.writeNbt(this.nbtPlayerData);
    }
    public static void send(ServerPlayerEntity player) {
        IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
        SyncPacket payload = new SyncPacket(nenPlayer.nen$saveDataToNbt());
        ServerPlayNetworking.send(player, payload);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void receive(SyncPacket payload, ClientPlayNetworking.Context context) {
        context.client().executeTask(() -> {
            IPlayerEntityNen nenPlayer = null;
            try {
                nenPlayer = (IPlayerEntityNen) Objects.requireNonNull(context.client().player);
            } catch (NullPointerException ignore) {
            }
            if (nenPlayer != null) {
                nenPlayer.nen$loadDataFromNbt(payload.nbtPlayerData);
            }
        });
    }
}