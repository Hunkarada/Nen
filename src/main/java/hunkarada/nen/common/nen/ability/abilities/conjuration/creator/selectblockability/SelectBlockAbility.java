package hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability;

import hunkarada.nen.common.nen.ability.abstraction.ability.TargetAbility;
import net.minecraft.util.math.Vec3d;

public class SelectBlockAbility extends TargetAbility {
    public SelectBlockAbility(){
        id = "select_block_ability";
        targetType = TargetType.BLOCK;
        spellDistance = 16;
        staticCost = 0;
        dynamicCostPercent = 0;
        abilityEffect = new SelectBlockAbilityEffect();
        initialCooldown = 0;
    }

    @Override
    protected void spawnVisualEntities() {

    }

    @Override
    protected void spawnVisualEntity(Vec3d pos) {

    }

    @Override
    protected Vec3d calcVisualEntityPos() {
        return null;
    }
}
