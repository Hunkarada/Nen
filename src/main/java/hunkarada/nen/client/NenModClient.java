package hunkarada.nen.client;

import net.fabricmc.api.ClientModInitializer;

import static hunkarada.nen.common.register.NenModRegister.registerClient;

public class NenModClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        registerClient();
    }
}
