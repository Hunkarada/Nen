package hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability;

import hunkarada.nen.common.nen.ability.abstraction.ability.TargetAbility;

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

}
