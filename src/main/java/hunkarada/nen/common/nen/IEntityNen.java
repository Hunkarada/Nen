package hunkarada.nen.common.nen;

import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;

public interface IEntityNen {

//NenAbilityEffects
    ArrayList<AbilityEffect> nen$getNenAbilityEffects();
    void nen$setNenAbilityEffects(ArrayList<AbilityEffect> nenAbilityEffects);

    void nen$applyNenAbilityEffect(AbilityEffect nenAbilityEffect, PlayerEntity Player, double nenPower);
    void nen$removeNenAbilityEffect(AbilityEffect nenAbilityEffect);

    //NenMemory
    NenMemory nen$getNenMemory();
    void nen$setNenMemory(NenMemory nenMemory);
    void nen$writeToNenMemory(NenMemory.NenMemoryKey id, String data);
    String nen$readFromNenMemory(NenMemory.NenMemoryKey id);
}
