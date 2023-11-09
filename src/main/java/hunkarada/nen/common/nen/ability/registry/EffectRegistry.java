package hunkarada.nen.common.nen.ability.registry;

import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import hunkarada.nen.common.abstractions.Registry;

import java.util.HashMap;

public class EffectRegistry implements Registry<AbilityEffect> {


    private static final EffectRegistry instance = new EffectRegistry();

    private static final HashMap<String, AbilityEffect> registry = new HashMap<>();

    public void addToRegistry(String key, AbilityEffect value){
        registry.put(key, value);
    }

    @Override
    public AbilityEffect getFromRegistry(String key) {
        AbilityEffect value = registry.get(key);
        if (value == null){
            throw new NullPointerException("You should register AbilityEffect");
        }
        return value;
    }

    public static EffectRegistry getInstance() {
        return instance;
    }
}
