package hunkarada.nen.common.network;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.network.packet.CastPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {

    public static final Identifier CAST_PACKET_ID = new Identifier(NenMod.MOD_ID, "cast_packet")

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(CAST_PACKET_ID, CastPacket::receive);
    }
    public static void registerS2CPackets(){}
}
