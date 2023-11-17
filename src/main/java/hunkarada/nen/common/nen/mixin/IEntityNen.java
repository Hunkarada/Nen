package hunkarada.nen.common.nen.mixin;

import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;

import java.util.ArrayList;
import java.util.HashMap;

public interface IEntityNen {

    ArrayList<AbilityEffect> nen$getNenAbilityEffects();
    void nen$setNenAbilityEffects(ArrayList<AbilityEffect> nenAbilityEffects);


    public HashMap<String, String> nen$getNenMemory();
    void nen$setNenMemory(HashMap<String, String> nenMemory);
}
