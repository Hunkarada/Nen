package hunkarada.nen.common;

import hunkarada.nen.common.network.ModMessages;
import net.fabricmc.api.ModInitializer;

import static hunkarada.nen.common.register.NenModRegister.registerServer;

public class NenMod implements ModInitializer {
    public static final String MOD_ID = "nen";

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        registerServer();

        ModMessages.registerC2SPackets();

    }
}
