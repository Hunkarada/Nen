package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.register.registry.AbilitySetRegistry;

import java.util.ArrayList;
import java.util.HashMap;

public class AbilitySet implements CanRegister {
    protected String setId = "empty";
    protected ArrayList<Ability> abilitySet = new ArrayList<>();
    protected ArrayList<String> abilityIds = getAbilityIds();

    protected HashMap<String, Ability> abilityMap = new HashMap<>();
    public AbilitySet() {
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

    public HashMap<String, Ability> getAbilityMap(){
        HashMap<String, Ability> map = new HashMap<>();
        for (Ability ability : abilitySet){
            map.put(ability.id, ability);
        }
        return map;
    }

    public static String toNbt(AbilitySet nenAbilitySet){
       return nenAbilitySet.setId;
    }

    public static AbilitySet fromNbt(String id){
        return AbilitySetRegistry.getInstance().getFromRegistry(id);
    }

    @Override
    public void register() {
        AbilitySetRegistry.getInstance().addToRegistry(setId, this);
    }
}
