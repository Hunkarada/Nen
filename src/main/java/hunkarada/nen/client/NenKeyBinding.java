package hunkarada.nen.client;

import hunkarada.nen.common.network.ModMessages;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

public class NenKeyBinding {
    public static final String KEY_CATEGORY_TRANSLATE = "key.category.nen";
    private static KeyBinding keyCastFirst;
    private static KeyBinding keyCastSecond;
    private static KeyBinding keyCastThird;
    private static KeyBinding keyCastForth;
    private static KeyBinding keyCastFifth;
    private static KeyBinding keyCastModeSwitch;

    public static void registerKeyActionsCast() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastFirst.wasPressed()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt(0);
                ClientPlayNetworking.send(ModMessages.CAST_PACKET_ID, buf);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastSecond.wasPressed()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt(1);
                ClientPlayNetworking.send(ModMessages.CAST_PACKET_ID, buf);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastThird.wasPressed()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt(2);
                ClientPlayNetworking.send(ModMessages.CAST_PACKET_ID, buf);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastForth.wasPressed()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt(3);
                ClientPlayNetworking.send(ModMessages.CAST_PACKET_ID, buf);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastFifth.wasPressed()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt(4);
                ClientPlayNetworking.send(ModMessages.CAST_PACKET_ID, buf);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCastModeSwitch.wasPressed()){
                if (keyCastFirst.isUnbound()){
                    keyCastFirst.setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_1));
                    MinecraftClient.getInstance().options.hotbarKeys[0].setBoundKey(InputUtil.UNKNOWN_KEY);
                }
                else {
                    keyCastFirst.setBoundKey(InputUtil.UNKNOWN_KEY);
                    MinecraftClient.getInstance().options.hotbarKeys[0].setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_1));
                }

                if (keyCastSecond.isUnbound()){
                    keyCastSecond.setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_2));
                    MinecraftClient.getInstance().options.hotbarKeys[1].setBoundKey(InputUtil.UNKNOWN_KEY);
                }
                else {
                    keyCastSecond.setBoundKey(InputUtil.UNKNOWN_KEY);
                    MinecraftClient.getInstance().options.hotbarKeys[1].setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_2));
                }

                if (keyCastThird.isUnbound()){
                    keyCastThird.setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_3));
                    MinecraftClient.getInstance().options.hotbarKeys[2].setBoundKey(InputUtil.UNKNOWN_KEY);
                }
                else {
                    keyCastThird.setBoundKey(InputUtil.UNKNOWN_KEY);
                    MinecraftClient.getInstance().options.hotbarKeys[2].setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_3));
                }

                if (keyCastForth.isUnbound()){
                    keyCastForth.setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_4));
                    MinecraftClient.getInstance().options.hotbarKeys[3].setBoundKey(InputUtil.UNKNOWN_KEY);
                }
                else {
                    keyCastForth.setBoundKey(InputUtil.UNKNOWN_KEY);
                    MinecraftClient.getInstance().options.hotbarKeys[3].setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_4));
                }

                if (keyCastFifth.isUnbound()){
                    keyCastFifth.setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_5));
                    MinecraftClient.getInstance().options.hotbarKeys[4].setBoundKey(InputUtil.UNKNOWN_KEY);
                }
                else {
                    keyCastFifth.setBoundKey(InputUtil.UNKNOWN_KEY);
                    MinecraftClient.getInstance().options.hotbarKeys[4].setBoundKey(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_5));
                }
                KeyBinding.updateKeysByCode();
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
        keyCastModeSwitch = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nen.cast.modeswitch",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_TRANSLATE
        ));
        registerKeyActionsCast();
    }


}
