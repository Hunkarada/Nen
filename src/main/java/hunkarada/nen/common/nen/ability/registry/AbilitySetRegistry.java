package hunkarada.nen.common.nen.ability.registry;

import hunkarada.nen.common.abstractions.Registry;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenAbilitySet;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class AbilitySetRegistry implements Registry<NenAbilitySet> {
    private static final AbilitySetRegistry instance = new AbilitySetRegistry();
    private static final HashMap<String, NenAbilitySet> registry = new HashMap<>();

    public void addToRegistry(String key, NenAbilitySet value){
        registry.put(key, value);
    }

    @Override
    public NenAbilitySet getFromRegistry(String key) {
        NenAbilitySet value = registry.get(key);
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
