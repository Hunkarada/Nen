package hunkarada.nen.common.nen.mixin;

import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;

import java.util.ArrayList;
import java.util.HashMap;

public interface IEntityNen {

//NenAbilityEffects
    ArrayList<AbilityEffect> nen$getNenAbilityEffects();
    void nen$setNenAbilityEffects(ArrayList<AbilityEffect> nenAbilityEffects);

    void nen$addNenAbilityEffect(AbilityEffect nenAbilityEffect);
    void nen$removeNenAbilityEffect(AbilityEffect nenAbilityEffect);

//NenMemory
    public HashMap<String, String> nen$getNenMemory();
    void nen$setNenMemory(HashMap<String, String> nenMemory);
    void nen$writeToNenMemory(String id, String data);
    String nen$readFromNenMemory(String id);
}
