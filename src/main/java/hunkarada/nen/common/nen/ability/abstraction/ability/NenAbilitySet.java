package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.register.registry.AbilitySetRegistry;

import java.util.ArrayList;

public class NenAbilitySet implements CanRegister {
    protected String id = "empty";
    protected ArrayList<Ability> abilitySet = new ArrayList<>();
    public NenAbilitySet() {
        prepareSet();
    }

    protected void prepareSet() {
    }

    public ArrayList<Ability> getAbilitySetCopy(){
        return abilitySet;
    }

    public static String toNbt(NenAbilitySet nenAbilitySet){
       return nenAbilitySet.id;
    }

    public static NenAbilitySet fromNbt(String id){
        return AbilitySetRegistry.getInstance().getFromRegistry(id);
    }

    @Override
    public void register() {
        AbilitySetRegistry.getInstance().addToRegistry(id, this);
    }
}
