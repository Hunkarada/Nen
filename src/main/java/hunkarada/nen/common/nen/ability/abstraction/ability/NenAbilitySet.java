package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.register.registry.AbilitySetRegistry;

import java.util.ArrayList;

public class NenAbilitySet implements CanRegister {
    protected String setId = "empty";
    protected ArrayList<Ability> abilitySet = new ArrayList<>();
    public NenAbilitySet() {
        prepareSet();
    }

    protected void prepareSet() {
    }

    public ArrayList<Ability> getAbilitySetCopy(){
        return abilitySet;
    }
    public ArrayList<String> getAbilityIds(){
        ArrayList<String> abilityIds = new ArrayList<>();
        for (Ability ability: abilitySet){
            abilityIds.add(ability.id);
        }
        return abilityIds;
    }

    public static String toNbt(NenAbilitySet nenAbilitySet){
       return nenAbilitySet.setId;
    }

    public static NenAbilitySet fromNbt(String id){
        return AbilitySetRegistry.getInstance().getFromRegistry(id);
    }

    @Override
    public void register() {
        AbilitySetRegistry.getInstance().addToRegistry(setId, this);
    }
}
