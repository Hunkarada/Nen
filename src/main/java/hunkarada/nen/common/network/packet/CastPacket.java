package hunkarada.nen.common.network.packet;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class CastPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender)
    {
        // validate is player available to cast abilities and validate range of a packet is 0-4.
        IPlayerEntityNen nenPlayer = (IPlayerEntityNen) player;
        if (nenPlayer.nen$getIsNenAwakened()) {
            int value = buf.readInt();
            if (value >= 0 && value <= 4) {
                nenPlayer.nen$getNenAbilities().getAbilitySetCopy().get(value).cast(player);
            }
        }
    }
}
