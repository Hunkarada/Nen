package hunkarada.nen.common;

import hunkarada.nen.common.network.ModMessages;
import net.fabricmc.api.ModInitializer;

public class NenMod implements ModInitializer {
    public static final String MOD_ID = "nen";
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {

        ModMessages.registerC2SPackets();

    }
}
