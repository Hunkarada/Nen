package hunkarada.nen.common.nen.ability.abilities.conjuration.creator;

import hunkarada.nen.common.nen.ability.abilities.EmptyAbility;
import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.throwblockability.ThrowBlockAbility;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenClass;

import java.util.ArrayList;

public class CreatorNenClass extends NenClass {
   public CreatorNenClass(){
       super();
   }

    @Override
    public void generateClassAbilities() {
        classAbilities = new ArrayList<>();
        classAbilities.add(new ThrowBlockAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
    }

    @Override
    public void setClassId() {
       this.id = "creator_nen_class";
    }
}
