package hunkarada.nen.common.nen.ability.abilities;

import hunkarada.nen.common.nen.ability.abstraction.ability.NenClass;

import java.util.ArrayList;

public class EmptyNenClass extends NenClass {
    public EmptyNenClass(){
        id = "empty_nen_class";
        classAbilities = new ArrayList<>();
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
    }
}
