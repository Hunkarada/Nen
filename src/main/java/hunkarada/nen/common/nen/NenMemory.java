package hunkarada.nen.common.nen;

import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.Set;

public class NenMemory {
    private final HashMap<String, String> data = new HashMap<>();
    public static final NenMemoryKey SELECTED_BLOCK = new NenMemoryKey("selected_block");

    public static class NenMemoryKey {
        String encapsulatedKey;

        public NenMemoryKey(String encapsulatedKey){
            this.encapsulatedKey = encapsulatedKey;
        }
    }

    public static HashMap<String, String> toHashMap(NenMemory nenMemory){
        return nenMemory.data;
    }

    public void write(NenMemoryKey key, String value){
        data.put(key.encapsulatedKey, value);
    }

    public String read(NenMemoryKey key){
        return data.get(key.encapsulatedKey);
    }

    public static NbtCompound toNbt(NenMemory nenMemory){
        NbtCompound nenMemoryNbt = new NbtCompound();
        Set<String> keySet = NenMemory.toHashMap(nenMemory).keySet();
        for (String key : keySet){
            NbtCompound pairNbt = new NbtCompound();
            pairNbt.putString("key", key);
            pairNbt.putString("value", nenMemory.read(new NenMemory.NenMemoryKey(key)));
            nenMemoryNbt.put(key, pairNbt);
        }
        return nenMemoryNbt;
    }

    public static NenMemory fromNbt(NbtCompound nbt){
        NenMemory nenMemory = new NenMemory();
        NbtCompound nenMemoryNbt = nbt.getCompound("nenMemory");
        String[] memoryKeys = nbt.getCompound("nenMemory").getKeys().toArray(new String[0]);
        for (String key : memoryKeys){
            NbtCompound pairNbt = nenMemoryNbt.getCompound(key);
            nenMemory.write(new NenMemory.NenMemoryKey(pairNbt.getString("key")), pairNbt.getString("value"));
        }
        return nenMemory;
    }
}
