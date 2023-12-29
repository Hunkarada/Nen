package hunkarada.nen.common.register;

import hunkarada.nen.client.NenKeyBinding;
import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.ability.abilitysets.conjuration.creator.CreatorNenAbilitySet;
import hunkarada.nen.common.nen.ability.abilitysets.conjuration.creator.selectblockability.SelectBlockAbility;
import hunkarada.nen.common.nen.ability.abilitysets.conjuration.creator.selectblockability.SelectBlockAbilityEffect;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenAbilitySet;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenAbilityEntity;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenCollisionEntity;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenProjectileEntity;
import hunkarada.nen.common.network.ModMessages;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class NenModRegister {

    public static void registerClient(){
        registerClientEntities();
        registerKeybindings();
    }
    public static void registerCommon(){
        registerEntities();
        registerAbilities();
        registerEffects();
        registerAbilitySets();
        registerNetwork();
    }

    public static void registerAbilities(){
        new SelectBlockAbility().register();
    }

    public static void registerEffects(){
        new SelectBlockAbilityEffect().register();
    }

    public static void registerAbilitySets(){
        new NenAbilitySet().register();
        new CreatorNenAbilitySet().register();
    }
    public static void registerEntities(){
        final EntityType<NenAbilityEntity> NEN_ABILITY_ENTITY = Registry.register(Registries.ENTITY_TYPE, new Identifier(NenMod.MOD_ID, "nen_ability_entity"),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenAbilityEntity::new).dimensions(EntityDimensions.fixed(0, 0)).build());
        final EntityType<NenCollisionEntity> NEN_COLLISION_ENTITY = Registry.register(Registries.ENTITY_TYPE, new Identifier(NenMod.MOD_ID, "nen_collision_entity"),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenCollisionEntity::new).dimensions(EntityDimensions.fixed(0,0)).build());
        final EntityType<NenProjectileEntity> NEN_PROJECTILE_ENTITY = Registry.register(Registries.ENTITY_TYPE, new Identifier(NenMod.MOD_ID, "nen_projectile_entity"),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenProjectileEntity::new).dimensions(EntityDimensions.fixed(0, 0)).build());
    }

    public static void registerClientEntities(){

    }

    public static void registerNetwork(){
        ModMessages.registerC2SPackets();
        ModMessages.registerS2CPackets();
    }

    public static void registerKeybindings(){
        NenKeyBinding.register();
    }
}
