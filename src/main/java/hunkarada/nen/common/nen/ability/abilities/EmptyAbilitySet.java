package hunkarada.nen.common.nen.ability.abilities;

import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;

public class EmptyAbilitySet extends AbilitySet {

    @Override
    protected void prepareSet() {
        this.setId = "empty";
    }
}
