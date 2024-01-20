package hunkarada.nen.common.network.packet;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class SyncPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        IPlayerEntityNen nenPlayer = (IPlayerEntityNen) Objects.requireNonNull(client.player);
        nenPlayer.nen$setDataFromPacket(buf.readBoolean(), buf.readLong(), buf.readLong(), buf.readInt(), buf.readLong(), buf.readLong(), NenType.fromNbt(buf.readString()), AbilitySet.fromNbtPacket(Objects.requireNonNull(buf.readNbt())));
    }
    public static void send(ServerPlayerEntity player, Identifier channelName, PacketByteBuf buf) {
        IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
        buf.writeBoolean(nenPlayer.nen$getIsNenAwakened());
        buf.writeLong(nenPlayer.nen$getNenPower());
        buf.writeLong(nenPlayer.nen$getNenPowerCap());
        buf.writeInt(nenPlayer.nen$getNenLvl());
        buf.writeLong(nenPlayer.nen$getNenExp());
        buf.writeLong(nenPlayer.nen$getNenExpUntilNextLvl());
        buf.writeString(NenType.toNbt(nenPlayer.nen$getNenType()));
//        this.nenRestrictions = new ArrayList<>();
        buf.writeNbt(AbilitySet.toNbt(nenPlayer.nen$getNenAbilities()));
        ServerPlayNetworking.send(player, channelName, buf);
    }
}