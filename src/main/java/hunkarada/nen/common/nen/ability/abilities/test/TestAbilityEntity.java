package hunkarada.nen.common.nen.ability.abilities.test;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenAbilityEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TestAbilityEntity extends NenAbilityEntity {

    public static final Identifier TEST_ABILITY_ID = new Identifier(NenMod.MOD_ID, "test_ability_entity");
    public TestAbilityEntity(EntityType<?> type, World world) {
        super(type, world);
    }
}
