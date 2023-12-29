package hunkarada.nen.common.register.registry;

import hunkarada.nen.common.abstractions.Registry;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class AbilitySetRegistry implements Registry<AbilitySet> {
    private static final AbilitySetRegistry instance = new AbilitySetRegistry();
    private static final HashMap<String, AbilitySet> registry = new HashMap<>();

    public void addToRegistry(String key, AbilitySet value){
        registry.put(key, value);
    }

    @Override
    public AbilitySet getFromRegistry(String key) {
        AbilitySet value = registry.get(key);
        if (value == null){
            throw new NullPointerException("You should register AbilitySet");
        }
        try {
            value = value.getClass().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static AbilitySetRegistry getInstance(){
        return instance;
    }
}
