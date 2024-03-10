package hunkarada.nen.client;

import hunkarada.nen.common.network.packet.CastPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class NenKeyBinding {
    public static final String KEY_CATEGORY_TRANSLATE = "key.category.nen";
    private static KeyBinding keyCastFirst;
    private static KeyBinding keyCastSecond;
    private static KeyBinding keyCastThird;
    private static KeyBinding keyCastForth;
    private static KeyBinding keyCastFifth;

    public static void registerKeyActionsCast() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastFirst.wasPressed()) {
                CastPacket.send(0);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastSecond.wasPressed()) {
                CastPacket.send(1);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastThird.wasPressed()) {
                CastPacket.send(2);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastForth.wasPressed()) {
                CastPacket.send(3);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastFifth.wasPressed()) {
                CastPacket.send(4);
            }
        });
    }

    public static void registerKeys() {
        keyCastFirst = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast.first",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyCastSecond = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast.second",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyCastThird = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast.third",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyCastForth = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast.forth",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyCastFifth = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast.fifth",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));
        registerKeyActionsCast();
    }


}
