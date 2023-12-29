package hunkarada.nen.common.register;

import hunkarada.nen.client.NenKeyBinding;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.CreatorAbilitySet;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability.SelectBlockAbility;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability.SelectBlockAbilityEffect;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenAbilityEntity;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenCollisionEntity;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenProjectileEntity;
import hunkarada.nen.common.network.ModMessages;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

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
        new AbilitySet().register();
        new CreatorAbilitySet().register();
    }
    //works for both sides
    public static void registerEntities(){
        Registry.register(Registries.ENTITY_TYPE, NenAbilityEntity.NEN_ABILITY_ENTITY_ID,
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenAbilityEntity::new).dimensions(EntityDimensions.fixed(0, 0)).build());
        Registry.register(Registries.ENTITY_TYPE, NenCollisionEntity.NEN_COLLISION_ENTITY_ID,
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, NenCollisionEntity::new).dimensions(EntityDimensions.fixed(0,0)).build());
        Registry.register(Registries.ENTITY_TYPE, NenProjectileEntity.NEN_PROJECTILE_ENTITY,
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
