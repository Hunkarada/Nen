package hunkarada.nen.common.nen;

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
    public NenMemory nen$getNenMemory();
    void nen$setNenMemory(NenMemory nenMemory);
    void nen$writeToNenMemory(NenMemory.NenMemoryKey id, String data);
    String nen$readFromNenMemory(NenMemory.NenMemoryKey id);
}
