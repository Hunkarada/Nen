package hunkarada.nen.common.nen.ability.abilitysets.conjuration.creator;

import hunkarada.nen.common.nen.ability.abilitysets.conjuration.creator.selectblockability.SelectBlockAbility;
import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenAbilitySet;

import java.util.ArrayList;

public class CreatorNenAbilitySet extends NenAbilitySet {
    @Override
    protected void prepareSet(){
       this.setId = "creatorAbilitySet";
       ArrayList<Ability> abilitySet = new ArrayList<>();
       abilitySet.add(new SelectBlockAbility());
       this.abilitySet = abilitySet;
    }
}
