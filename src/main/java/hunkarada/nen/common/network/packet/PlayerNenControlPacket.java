package hunkarada.nen.common.network.packet;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PlayerNenControlPacket(int value) implements CustomPayload {
    public static final CustomPayload.Id<PlayerNenControlPacket> ID = new CustomPayload.Id<>(Identifier.of(NenMod.MOD_ID, "player_nen_control_packet"));
    public static final PacketCodec<RegistryByteBuf, PlayerNenControlPacket> CODEC = CustomPayload.codecOf(PlayerNenControlPacket::write, PlayerNenControlPacket::new).cast();

    public PlayerNenControlPacket(RegistryByteBuf buf){
        this(buf.readInt());
    }
    public void write(RegistryByteBuf buf){
        buf.writeInt(value);
    }
    public static void receive(PlayerNenControlPacket payload, ServerPlayNetworking.Context context){
        context.player().server.execute(() -> {
            switch (payload.value) {
                // activate nen
                case 0 -> {
                    IPlayerEntityNen nenPlayer = (IPlayerEntityNen) context.player();
                    nenPlayer.nen$activateNenSwitch();
                }
                case 1 -> {
                // hide nen
                    IPlayerEntityNen nenPlayer = (IPlayerEntityNen) context.player();
                    nenPlayer.nen$hideNenSwitch();
                }
                // can see nen
                case 2 -> {
                    IPlayerEntityNen nenPlayer = (IPlayerEntityNen) context.player();
                    nenPlayer.nen$canSeeNenSwitch();
                }
            }
        });
    }
    public static void send(int index) {
        PlayerNenControlPacket payload = new PlayerNenControlPacket(index);
        ClientPlayNetworking.send(payload);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
