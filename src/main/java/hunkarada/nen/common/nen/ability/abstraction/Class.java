package hunkarada.nen.common.nen.ability.abstraction;

import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;

import java.util.ArrayList;

public abstract class Class {
    protected ArrayList<Ability> classAvailableAbilities;
    public Class(){

    }
    public ArrayList<Ability> getClassAvailableAbilities() {
        return classAvailableAbilities;
    }

}
