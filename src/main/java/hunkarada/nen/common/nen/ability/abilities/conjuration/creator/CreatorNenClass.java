package hunkarada.nen.common.nen.ability.abilities.conjuration.creator;

import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability.SelectBlockAbility;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenClass;

import java.util.ArrayList;

public class CreatorNenClass extends NenClass {
   CreatorNenClass(){
       classAbilities = new ArrayList<>();
       classAbilities.set(4, new SelectBlockAbility());
   }
}
