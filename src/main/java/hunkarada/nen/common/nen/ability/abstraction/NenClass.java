package hunkarada.nen.common.nen.ability.abstraction;

import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.register.registry.NenClassRegistry;

import java.util.ArrayList;

public abstract class NenClass {
    protected String id;
    protected Ability classAbility;
    protected ArrayList<Ability> classAvailableAbilities;
    public NenClass(){

    }
    public static String toNbt(NenClass nenClass){
        return nenClass.id;
    }
    public static NenClass fromNbt(String id){
        return NenClassRegistry.getInstance().getFromRegistry(id);
    }
    public ArrayList<Ability> getClassAvailableAbilities() {
        return classAvailableAbilities;
    }

}
