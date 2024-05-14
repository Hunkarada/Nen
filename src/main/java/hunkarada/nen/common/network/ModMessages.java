package hunkarada.nen.common.network;

import hunkarada.nen.common.network.packet.AwakeNenPacket;
import hunkarada.nen.common.network.packet.CastPacket;
import hunkarada.nen.common.network.packet.PlayerNenControlPacket;
import hunkarada.nen.common.network.packet.SyncPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ModMessages {

    public static void registerC2SPackets(){
        // registering Codec and Receiver.
        PayloadTypeRegistry.playC2S().register(CastPacket.ID, CastPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(CastPacket.ID, CastPacket::receive);

        PayloadTypeRegistry.playC2S().register(AwakeNenPacket.ID, AwakeNenPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(AwakeNenPacket.ID, AwakeNenPacket::receive);

        PayloadTypeRegistry.playC2S().register(PlayerNenControlPacket.ID, PlayerNenControlPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(PlayerNenControlPacket.ID, PlayerNenControlPacket::receive);

    }
    public static void registerS2CPackets(){
        PayloadTypeRegistry.playS2C().register(SyncPacket.ID, SyncPacket.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(SyncPacket.ID, SyncPacket::receive);
    }
}
