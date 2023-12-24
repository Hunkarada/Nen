package hunkarada.nen.common.network;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.network.packet.C2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier SOME_ID = new Identifier(NenMod.MOD_ID, "some");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(SOME_ID, C2SPacket::receive);
    }
    public static void registerS2CPackets(){}
}
