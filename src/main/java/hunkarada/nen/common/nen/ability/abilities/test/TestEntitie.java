package hunkarada.nen.common.nen.ability.abilities.test;


import hunkarada.nen.common.NenMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import javax.swing.text.html.parser.Entity;

public class TestEntitie {
    public static final EntityType<TestAbilityEntity> TEST = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(NenMod.MOD_ID, "nentest"), FabricEntityTypeBuilder.<TestAbilityEntity>create(SpawnGroup.MISC, TestAbilityEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
}
