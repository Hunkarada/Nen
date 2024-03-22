package hunkarada.nen.common.network;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.network.packet.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {

    public static final Identifier CAST_PACKET_ID = new Identifier(NenMod.MOD_ID, "cast_packet");
    public static final Identifier AWAKE_NEN_PACKET_ID = new Identifier(NenMod.MOD_ID, "awake_nen_packet");
    public static final Identifier SYNC_PACKET_ID = new Identifier(NenMod.MOD_ID, "sync_packet");
    public static final Identifier MANIPULATE_ABILITY_SET_PACKET_ID = new Identifier(NenMod.MOD_ID, "manipulate_ability_set");
    public static final Identifier PLAYER_NEN_CONTROL_PACKET = new Identifier(NenMod.MOD_ID, "player_nen_control_packet");


    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(CAST_PACKET_ID, CastPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(AWAKE_NEN_PACKET_ID, AwakeNenPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(MANIPULATE_ABILITY_SET_PACKET_ID, ManipulateAbilitySetPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(PLAYER_NEN_CONTROL_PACKET, PlayerNenControlPacket::receive);

    }
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(SYNC_PACKET_ID, SyncPacket::receive);
    }
}
