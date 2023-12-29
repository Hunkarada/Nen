package hunkarada.nen.common.nen;


import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;
import hunkarada.nen.common.nen.restriction.Restriction;

import java.util.ArrayList;

public interface IPlayerEntityNen {

     boolean nen$getIsNenAwakened();

     void nen$setIsNenAwakened(boolean isNenAwakened);

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


     AbilitySet nen$getNenAbilities();

     void nen$setNenAbilities(AbilitySet nenAbilities);





     boolean nen$collectNen(long sum);

     void nen$giveNen(long value);
}
