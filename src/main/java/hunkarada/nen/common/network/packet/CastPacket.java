package hunkarada.nen.common.network.packet;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record CastPacket(int value) implements CustomPayload {
    public static final CustomPayload.Id<CastPacket> ID = new Id<>(Identifier.of(NenMod.MOD_ID, "cast_packet"));
    public static final PacketCodec<RegistryByteBuf, CastPacket> CODEC = CustomPayload.codecOf(CastPacket::write, CastPacket::new).cast();
    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
    public CastPacket(RegistryByteBuf buf){
        this(buf.readInt());
    }
    public void write(RegistryByteBuf buf){
        buf.writeInt(value);
    }
    public static void receive(CastPacket payload, ServerPlayNetworking.Context context) {
        if (!context.player().isDisconnected()) {
            IPlayerEntityNen nenPlayer = (IPlayerEntityNen) context.player();
            if (payload.value >= 0 && payload.value <= 5) {
                nenPlayer.nen$getNenAbilities().getAbilitySet().get(payload.value).cast(context.player());
            }
        }
    }
    public static void send(int index){
        ClientPlayNetworking.send(new CastPacket(index));
    }

}
