package hunkarada.nen.common.nen.ability.abilities;

import hunkarada.nen.common.nen.ability.abstraction.ability.NenClass;

import java.util.ArrayList;

public class EmptyNenClass extends NenClass {
    public EmptyNenClass(){
        id = "empty_nen_class";
        ultimateClassAbility = new EmptyAbility();
        classAvailableAbilities = new ArrayList<>();
        classAvailableAbilities.add(new EmptyAbility());
        classAvailableAbilities.add(new EmptyAbility());
        classAvailableAbilities.add(new EmptyAbility());
        classAvailableAbilities.add(new EmptyAbility());
    }
}
