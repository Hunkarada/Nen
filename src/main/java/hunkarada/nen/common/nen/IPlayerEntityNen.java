package hunkarada.nen.common.nen;


import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenClass;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenClassSet;
import hunkarada.nen.common.nen.restriction.Restriction;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;

public interface IPlayerEntityNen {
     NbtCompound nen$saveDataToNbt();
     void nen$loadDataFromNbt(NbtCompound nbt);
     boolean nen$getIsNenAwakened();
     int nen$getNenLvl();
     void nen$addNenLvl();
     double nen$getNenPowerCap();
     double nen$getNenPower();
     long nen$getNenExp();
     long nen$getNenExpUntilNextLvl();
     void nen$addNenExp(int exp);
     AbilitySet nen$getNenAbilities();
     ArrayList<Restriction> nen$getNenRestrictions();
     NenClassSet nen$getNenUnlockedClasses();
     NenClass nen$getNenClass();
     boolean nen$collectNen(double value);
     void nen$giveNen(double value);
     void nen$awakePlayer();
     void nen$addExp(long nenExp);
     boolean nen$checkLvlUp();
     void nen$lvlUp();
     void nen$regenNen();
     boolean nen$getIsNenActive();
     void nen$activateNenSwitch();
     void nen$blockNen(int time);
     void nen$unblockNen();
     void nen$hideNenSwitch();
     void nen$canSeeNenSwitch();
}
