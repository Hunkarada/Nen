package hunkarada.nen.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class NenKeyBinding {
    public static final String KEY_CATEGORY_TRANSLATE = "key.category.nen";
    private static KeyBinding keyBinding;

    public static void registerKeyInput() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.wasPressed()) {

            }
        });
    }

    public static void register() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_TRANSLATE
        ));

        registerKeyInput();
    }


}
