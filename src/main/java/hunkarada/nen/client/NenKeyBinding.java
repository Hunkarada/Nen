package hunkarada.nen.client;

import hunkarada.nen.common.network.packet.CastPacket;
import hunkarada.nen.common.network.packet.PlayerNenControlPacket;
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
    private static KeyBinding keyCastSixth;
    private static KeyBinding keyActivateNen;
    private static KeyBinding keyHideNen;
    private static KeyBinding keyCanSeeNen;

    private static void registerKeyActionsCast() {
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
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastSixth.wasPressed()){
                CastPacket.send(5);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyActivateNen.wasPressed()) {
                // see PlayerNenControlPacket.class
                PlayerNenControlPacket.send(0);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyHideNen.wasPressed()) {
                // see PlayerNenControlPacket.class
                PlayerNenControlPacket.send(1);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCanSeeNen.wasPressed()) {
                // see PlayerNenControlPacket.class
                PlayerNenControlPacket.send(2);
            }
        });
    }

    public static void registerKeys() {
        keyCastFirst = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast_first_spell",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyCastSecond = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast_second_spell",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyCastThird = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast_third_spell",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyCastForth = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast_forth_spell",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyCastFifth = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast_fifth_spell",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));
        keyCastSixth = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast_sixth_spell",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyActivateNen = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.activate_nen",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));

        keyHideNen = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.hide_nen",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));
        keyCanSeeNen = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.can_see",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_TRANSLATE
        ));
        registerKeyActionsCast();
    }


}
