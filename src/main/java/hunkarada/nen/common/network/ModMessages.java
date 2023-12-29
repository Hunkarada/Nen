package hunkarada.nen.common.network;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.network.packet.CastPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(new Identifier(NenMod.MOD_ID, "cast_packet"), CastPacket::receive);
    }
    public static void registerS2CPackets(){}
}
