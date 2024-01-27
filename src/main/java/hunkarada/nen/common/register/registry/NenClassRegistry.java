package hunkarada.nen.common.register.registry;

import hunkarada.nen.common.abstractions.Registry;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenClass;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class NenClassRegistry implements Registry<NenClass> {

    private static final NenClassRegistry instance = new NenClassRegistry();
    private static final HashMap<String, NenClass> registry = new HashMap<>();

    public void addToRegistry(String key, NenClass value){
        registry.put(key, value);
    }

    @Override
    public NenClass getFromRegistry(String key) {
        NenClass value = registry.get(key);
        if (value == null){
            throw new NullPointerException("You should register NenClass");
        }
        try {
            value = value.getClass().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static NenClassRegistry getInstance(){
        return instance;
    }
}
