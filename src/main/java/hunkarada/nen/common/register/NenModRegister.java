package hunkarada.nen.common.register;

import hunkarada.nen.client.NenKeyBinding;
import hunkarada.nen.client.gui.AbilityGridRenderer;
import hunkarada.nen.client.gui.BattleModeRenderer;
import hunkarada.nen.common.nen.event.ReturnDataOnPlayerDeath;
import hunkarada.nen.common.network.event.OnEndServerTick;
import hunkarada.nen.common.nen.ability.abilities.EmptyAbility;
import hunkarada.nen.common.nen.ability.abilities.EmptyNenClass;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability.SelectBlockAbility;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability.SelectBlockAbilityEffect;
import hunkarada.nen.common.network.ModMessages;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class NenModRegister {

    public static void registerClient(){
        registerClientEntities();
        registerNetworkClient();
        registerKeybindings();
        registerHud();
    }
    public static void registerCommon(){
        registerEntities();
        registerAbilities();
        registerEffects();
        registerNenClasses();
        registerNetwork();
        registerEvents();
    }

    private static void registerAbilities(){
        new SelectBlockAbility().register();
        new EmptyAbility().register();
    }

    private static void registerEffects(){
        new SelectBlockAbilityEffect().register();
    }

    private static void registerNenClasses(){
        new EmptyNenClass().register();
    }
    //works for both sides
    private static void registerEntities(){
//        NEN_ABILITY_ENTITY = Registry.register(Registries.ENTITY_TYPE, NenAbilityEntity.NEN_ABILITY_ENTITY_ID,
//                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenAbilityEntity::new).dimensions(EntityDimensions.fixed(0, 0)).build());
//        NEN_COLLISION_ENTITY = Registry.register(Registries.ENTITY_TYPE, NenCollisionEntity.NEN_COLLISION_ENTITY_ID,
//                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenCollisionEntity::new).dimensions(EntityDimensions.fixed(0,0)).build());
//        NEN_PROJECTILE_ENTITY = Registry.register(Registries.ENTITY_TYPE, NenProjectileEntity.NEN_PROJECTILE_ENTITY,
//                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenProjectileEntity::new).dimensions(EntityDimensions.fixed(0, 0)).build());
//        Registry.register(Registries.ENTITY_TYPE,
//                new Identifier(NenMod.MOD_ID, "nentest"), FabricEntityTypeBuilder.<TestAbilityEntity>create(SpawnGroup.MISC, TestAbilityEntity::new)
//                        .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    }

    public static void registerClientEntities(){
//        EntityRendererRegistry.register(TEST, FlyingItemEntityRenderer::new);

    }

    private static void registerNetwork(){
        ModMessages.registerC2SPackets();
    }
    private static void registerNetworkClient(){
        ModMessages.registerS2CPackets();
    }

    private static void registerKeybindings(){
        NenKeyBinding.registerKeys();
    }

    private static void registerHud(){
        HudRenderCallback.EVENT.register(AbilityGridRenderer::onHudRender);
        HudRenderCallback.EVENT.register(BattleModeRenderer::onHudRender);
    }
    private static void registerEvents(){
        ServerTickEvents.END_SERVER_TICK.register(new OnEndServerTick());
        ServerPlayerEvents.COPY_FROM.register(new ReturnDataOnPlayerDeath());
    }
}