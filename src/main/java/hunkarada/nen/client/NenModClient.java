package hunkarada.nen.client;

import hunkarada.nen.common.nen.NenKeyBinding;
import net.fabricmc.api.ClientModInitializer;

public class NenModClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        NenKeyBinding.register();

    }
}
