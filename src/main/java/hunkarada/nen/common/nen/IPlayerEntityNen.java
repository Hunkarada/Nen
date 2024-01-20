package hunkarada.nen.common.nen;


import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;
import hunkarada.nen.common.nen.restriction.Restriction;

import java.util.ArrayList;

public interface IPlayerEntityNen {
     boolean nen$getIsNenAwakened();
     NenType nen$getNenType();
     void nen$setNenType(NenType nenType);
     int nen$getNenLvl();
     void nen$addNenLvl();
     long nen$getNenPowerCap();
     long nen$getNenPower();
     long nen$getNenExp();
     long nen$getNenExpUntilNextLvl();
     void nen$addNenExp(int exp);
     AbilitySet nen$getNenAbilities();
     ArrayList<Restriction> nen$getNenRestrictions();
     public ArrayList<Ability> nen$getNenAvailableAbilities();
     boolean nen$collectNen(long value);
     void nen$giveNen(long value);
     void nen$awakePlayer();
     void nen$setDataFromPacket(
        boolean isNenAwakened,
        long nenPower,
        long nenPowerCap,
        int nenLvl,
        long nenExp,
        long nenExpUntilNextLvl,
        NenType nenType,
        AbilitySet nenAbilities);
}
