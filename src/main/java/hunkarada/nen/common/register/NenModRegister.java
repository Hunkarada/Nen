package hunkarada.nen.common.register;

import hunkarada.nen.client.NenKeyBinding;
import hunkarada.nen.client.gui.AbilityGridRenderer;
import hunkarada.nen.client.gui.BattleModeRenderer;
import hunkarada.nen.common.event.OnPlayerLogin;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability.SelectBlockAbility;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability.SelectBlockAbilityEffect;
import hunkarada.nen.common.nen.ability.abilities.test.TestAbilityEntity;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenAbilityEntity;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenCollisionEntity;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenProjectileEntity;
import hunkarada.nen.common.network.ModMessages;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;

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
        registerAbilitySets();
        registerNetwork();
        registerEvents();
    }

    private static void registerAbilities(){
        new SelectBlockAbility().register();
    }

    private static void registerEffects(){
        new SelectBlockAbilityEffect().register();
    }

    private static void registerAbilitySets(){
    }
    public static EntityType<NenAbilityEntity> NEN_ABILITY_ENTITY;
    public static EntityType<NenCollisionEntity> NEN_COLLISION_ENTITY;
    public static EntityType<NenProjectileEntity> NEN_PROJECTILE_ENTITY;
    public static EntityType<TestAbilityEntity> TEST_ABILITY_ENTITY;
    //works for both sides
    private static void registerEntities(){
//        NEN_ABILITY_ENTITY = Registry.register(Registries.ENTITY_TYPE, NenAbilityEntity.NEN_ABILITY_ENTITY_ID,
//                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenAbilityEntity::new).dimensions(EntityDimensions.fixed(0, 0)).build());
//        NEN_COLLISION_ENTITY = Registry.register(Registries.ENTITY_TYPE, NenCollisionEntity.NEN_COLLISION_ENTITY_ID,
//                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenCollisionEntity::new).dimensions(EntityDimensions.fixed(0,0)).build());
//        NEN_PROJECTILE_ENTITY = Registry.register(Registries.ENTITY_TYPE, NenProjectileEntity.NEN_PROJECTILE_ENTITY,
//                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenProjectileEntity::new).dimensions(EntityDimensions.fixed(0, 0)).build());
    //    TEST_ABILITY_ENTITY = Registry.register(Registries.ENTITY_TYPE, TestAbilityEntity.TEST_ABILITY_ID, FabricEntityTypeBuilder.create(SpawnGroup.MISC, TestAbilityEntity::new).dimensions(EntityDimensions.fixed(0,0)).build());
    }

    private static void registerClientEntities(){

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
        ServerTickEvents.END_SERVER_TICK.register(new OnPlayerLogin());
    }
}

