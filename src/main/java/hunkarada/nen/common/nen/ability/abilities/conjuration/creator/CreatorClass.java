package hunkarada.nen.common.nen.ability.abilities.conjuration.creator;

import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability.SelectBlockAbility;
import hunkarada.nen.common.nen.ability.abstraction.Class;

import java.util.ArrayList;

public class CreatorClass extends Class {
   CreatorClass(){
       classAvailableAbilities = new ArrayList<>();
       classAvailableAbilities.set(4, new SelectBlockAbility());
   }
}
