package hunkarada.nen.common.nen.mixin;


import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import hunkarada.nen.common.nen.restriction.Restriction;

import java.util.ArrayList;
import java.util.HashMap;

public interface ILivingEntityNen {

     boolean nen$getIsNenAwakened();

     void nen$setIsNenAwakened(boolean isNenAwakened);
     int nen$getNenAbilityPoints();

     void nen$setNenAbilityPoints(int nenAbilityPoints);

     NenType nen$getNenType();

     void nen$setNenType(NenType nenType);


     int nen$getNenLvl();

     void nen$setNenLvl(int nenLvl);


     long nen$getNenPowerCap();

     void nen$setNenPowerCap(long nenPowerCap);


     long nen$getNenPower();

     void nen$setNenPower(long nenPower);

     int nen$getNenExp();

     void nen$setNenExp(int nenExp);


     ArrayList<Restriction> nen$getNenRestrictions();

     void nen$setNenRestrictions(ArrayList<Restriction> nenRestrictions);


     ArrayList<Ability> nen$getNenAbilities();

     void nen$setNenAbilities(ArrayList<Ability> nenAbilities);

     public HashMap<String, String> nen$getNenMemory();

     void nen$setNenMemory(HashMap<String, String> nenMemory);

     ArrayList<AbilityEffect> nen$getNenAbilityEffects();
     void nen$setNenAbilityEffects(ArrayList<AbilityEffect> nenAbilityEffects);

     void nen$writeToNenMemory(String id, String data);

     String nen$readFromNenMemory(String id);

     boolean nen$collectNen(long sum);

     void nen$giveNen(long value);
}
