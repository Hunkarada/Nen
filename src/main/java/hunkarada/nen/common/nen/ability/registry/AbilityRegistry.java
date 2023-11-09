package hunkarada.nen.common.nen.ability.registry;

import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.abstractions.Registry;

import java.util.HashMap;

public class AbilityRegistry implements Registry<Ability> {

    private static final AbilityRegistry instance = new AbilityRegistry();
    private static final HashMap<String, Ability> registry = new HashMap<>();

    public void addToRegistry(String key, Ability value){
        registry.put(key, value);
    }

    @Override
    public Ability getFromRegistry(String key) {
        Ability value = registry.get(key);
        if (value == null){
            throw new NullPointerException("You should register Ability");
        }
        return value;
    }

    public static AbilityRegistry getInstance(){
        return instance;
    }
}
