package hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability;

import hunkarada.nen.common.nen.ability.abstraction.ability.TargetAbility;

public class SelectBlockAbility extends TargetAbility {
    public SelectBlockAbility(){
       targetType = TargetType.BLOCK;
       cooldown = 1;

    }
}
