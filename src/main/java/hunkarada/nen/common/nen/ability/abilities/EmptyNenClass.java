package hunkarada.nen.common.nen.ability.abilities;

import hunkarada.nen.common.nen.ability.abstraction.ability.NenClass;

import java.util.ArrayList;

public class EmptyNenClass extends NenClass {
    public EmptyNenClass(){
        super();
    }
    @Override
    public void generateClassAbilities(){
        classAbilities = new ArrayList<>();
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
        classAbilities.add(new EmptyAbility());
    }

    @Override
    public void setClassId() {
        this.id = "empty_nen_class";
    }
}
