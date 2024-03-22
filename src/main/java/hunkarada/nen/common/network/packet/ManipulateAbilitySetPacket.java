package hunkarada.nen.common.network.packet;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;
import hunkarada.nen.common.network.ModMessages;
import hunkarada.nen.common.register.registry.AbilityRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ManipulateAbilitySetPacket {
    public static void sendAdd(Ability ability, int index){
       PacketByteBuf buf = PacketByteBufs.create();
       buf.writeInt(AbilitySet.AbilitySetActions.ADD.toInt());
       buf.writeString(ability.getId()); buf.writeInt(index);
       ClientPlayNetworking.send(ModMessages.MANIPULATE_ABILITY_SET_PACKET_ID, buf);
    }
    public static void sendRemove(Ability ability){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(AbilitySet.AbilitySetActions.REMOVE.toInt());
        buf.writeString(ability.getId());
        ClientPlayNetworking.send(ModMessages.MANIPULATE_ABILITY_SET_PACKET_ID, buf);
    }
    public static void sendSwap(Ability firstAbility, Ability secondAbility){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(AbilitySet.AbilitySetActions.SWAP.toInt());
        buf.writeString(firstAbility.getId()); buf.writeString(secondAbility.getId());
        ClientPlayNetworking.send(ModMessages.MANIPULATE_ABILITY_SET_PACKET_ID, buf);
    }
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        server.execute(() -> {
            if (!player.isDisconnected()){
                switch (buf.readInt()){
                    case 0 -> {
                        IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
                        nenPlayer.nen$addAbilityToHotbar(AbilityRegistry.getInstance().getFromRegistry(buf.readString()), buf.readInt());
                    }
                    case 1 -> {
                        IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
                        nenPlayer.nen$removeAbilityFromHotbar(AbilityRegistry.getInstance().getFromRegistry(buf.readString()));
                    }
                    case 2 -> {
                        IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
                        nenPlayer.nen$swapAbilitiesOnHotbar(AbilityRegistry.getInstance().getFromRegistry(buf.readString()), AbilityRegistry.getInstance().getFromRegistry(buf.readString()));
                    }
                }
            }
        });

    }
}
