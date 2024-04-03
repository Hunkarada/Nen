package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.register.registry.NenClassRegistry;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.Set;

public class NenClassSet {
    protected ArrayList<NenClass> classSet = new ArrayList<>();
    public void addClass(NenClass nenClass){
        if (!classSet.contains(nenClass)) {
            classSet.add(nenClass);
        }
    }
    public boolean contains(Ability ability){
        for (NenClass nenClass : classSet) {
            if (nenClass.classAbilities.contains(ability)){
                return true;
            }
        }
        return false;
    }

    public static NbtCompound toNbt(NenClassSet nenClassSet){
        ArrayList<NenClass> classes = nenClassSet.classSet;
        NbtCompound nbt = new NbtCompound();
        for (NenClass nenClass : classes){
            nbt.putString(nenClass.getId(), nenClass.getId());
        }
        return nbt;
    }

    public static NenClassSet fromNbt(NbtCompound nbt){
        NenClassSet classSet = new NenClassSet();
        Set<String> keys = nbt.getKeys();
        for (String key : keys){
            classSet.addClass(NenClassRegistry.getInstance().getFromRegistry(nbt.getString(key)));
        }
        return classSet;
    }
    public static NenClassSet fromNbtPacket(NbtCompound nbt){
        NenClassSet classSet = new NenClassSet();
        Set<String> keys = nbt.getKeys();
        for (String key : keys){
            classSet.addClass(NenClassRegistry.getInstance().getFromRegistry(nbt.getString(key)));
        }
        return classSet;
    }
}
