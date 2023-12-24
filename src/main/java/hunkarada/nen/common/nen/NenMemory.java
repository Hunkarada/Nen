package hunkarada.nen.common.nen;

import java.util.HashMap;

public class NenMemory {
    private HashMap<String, String> data = new HashMap<>();
    public static final NenMemoryKey SELECTED_BLOCK = new NenMemoryKey("selectedBlock");

    public static class NenMemoryKey {
        String encapsulatedKey;

        public NenMemoryKey(String encapsulatedKey){
            this.encapsulatedKey = encapsulatedKey;
        }
    }

    public static HashMap<String, String> toNbt(NenMemory nenMemory){
        return nenMemory.data;
    }

    public static void fromNbt(NenMemory nenMemory, HashMap<String, String> hashMap){
        nenMemory.data = hashMap;
    }

    public void write(NenMemoryKey key, String value){
        data.put(key.encapsulatedKey, value);
    }

    public String read(NenMemoryKey key){
        return data.get(key.encapsulatedKey);
    }
}
