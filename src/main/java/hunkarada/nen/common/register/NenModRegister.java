package hunkarada.nen.common.register;

import com.google.common.collect.ImmutableSet;
import hunkarada.nen.client.NenKeyBinding;
import hunkarada.nen.client.abilities.creator.ThrowBlockAbilityRenderer;
import hunkarada.nen.client.gui.NenHudRenderer;
import hunkarada.nen.common.nen.ability.abilities.EmptyAbility;
import hunkarada.nen.common.nen.ability.abilities.EmptyNenClass;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.CreatorNenClass;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.throwblockability.ThrowBlockAbilityEntity;
import hunkarada.nen.common.nen.event.OnPlayerDeath;
import hunkarada.nen.common.network.ModMessages;
import hunkarada.nen.common.network.event.OnEndServerTick;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAttachments;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;

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
        new EmptyAbility().register();
    }

    private static void registerEffects(){
    }

    private static void registerNenClasses(){
        new EmptyNenClass().register();
        new CreatorNenClass().register();
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
        THROW_BLOCK_ABILITY_ENTITY = Registry.register(Registries.ENTITY_TYPE, ThrowBlockAbilityEntity.THROW_BLOCK_ABILITY_ENTITY_ID,
                new EntityType<>(ThrowBlockAbilityEntity::new, SpawnGroup.MISC,
                        true, false, true, false, ImmutableSet.<Block>builder().build(),
                        new EntityDimensions(1, 1, 0.5f, EntityAttachments.builder().build(1, 1), true),
                        64, 1, 1, FeatureSet.empty())
        );
    }
    public static EntityType<ThrowBlockAbilityEntity> THROW_BLOCK_ABILITY_ENTITY;

    public static void registerClientEntities(){
//        EntityRendererRegistry.register(TEST, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(THROW_BLOCK_ABILITY_ENTITY, ThrowBlockAbilityRenderer::new);

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
        HudRenderCallback.EVENT.register((drawContext, drawContext2) -> NenHudRenderer.onHudRender(drawContext));
    }
    private static void registerEvents(){
        ServerTickEvents.END_SERVER_TICK.register(new OnEndServerTick());
        ServerPlayerEvents.COPY_FROM.register(new OnPlayerDeath());
    }
}