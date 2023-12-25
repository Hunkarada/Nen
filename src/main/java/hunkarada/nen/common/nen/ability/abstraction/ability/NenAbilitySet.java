package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.ability.registry.AbilitySetRegistry;

import java.util.HashMap;

public class NenAbilitySet implements CanRegister {
    protected String id = "empty";
    protected HashMap<String, Ability> abilitySet = new HashMap<>();
    public NenAbilitySet() {
        prepareSet();
    }

    protected void prepareSet() {
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
