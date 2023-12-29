package hunkarada.nen.common;

import net.fabricmc.api.ModInitializer;

import static hunkarada.nen.common.register.NenModRegister.registerCommon;

public class NenMod implements ModInitializer {
    public static final String MOD_ID = "nen";


    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        registerCommon();
    }
}
