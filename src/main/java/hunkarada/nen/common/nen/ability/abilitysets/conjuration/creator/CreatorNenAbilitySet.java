package hunkarada.nen.common.nen.ability.abilitysets.conjuration.creator;

import hunkarada.nen.common.nen.ability.abstraction.ability.NenAbilitySet;
import hunkarada.nen.common.nen.ability.abilitysets.conjuration.creator.selectblockability.SelectBlockAbility;
import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;

import java.util.HashMap;

public class CreatorNenAbilitySet extends NenAbilitySet {
    @Override
    protected void prepareSet(){
       this.id = "creatorAbilitySet";
       HashMap<String, Ability> abilitySet = new HashMap<>();
       abilitySet.put(new SelectBlockAbility().getId(), new SelectBlockAbility());
       this.abilitySet = abilitySet;
    }
}
