package hunkarada.nen.common.nen.mixin;

import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import hunkarada.nen.common.nen.restriction.Restriction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashMap;

// This class is mixing into every LivingEntity, and this adds Nen data to all living creatures in minecraft.
@Mixin(LivingEntity.class)
public abstract class LivingEntityNen
        extends Entity
        implements ILivingEntityNen {

    @Unique
    boolean isNenAwakened;
    @Unique
    long nenPower;
    // nen power cap is the maximum capacity of nen user, nen power is the current amount on nen
    @Unique
    long nenPowerCap;
    // nen level is current level of nen user. more lvl - more nenCap and more ability points.
    @Unique
    int nenLvl;
    @Unique
    int nenExp;
    // user's Type of nen, if you are a manipulator - you can use manipulation at 100% effectiveness. See ./src/resources/nen_types_explanation.jpg for details.
    @Unique
    NenType nenType;
    @Unique
    int nenAbilityPoints;
    // restrictions are something that restricts you and punish you if you won't follow restriction,
    // but it gives you more nen.
    @Unique
    ArrayList<Restriction> nenRestrictions;
    // abilities, which caster can use.
    @Unique
    ArrayList<Ability> nenAbilities;
    // effects, which caster has at himself.




    public LivingEntityNen(EntityType<?> type, World world) {
        super(type, world);
    }
    //here we are add variables for each entity.
    @Inject(method = "<init>", at = @At("RETURN"))
    public void LivingEntityNen(EntityType<?> type, World world, CallbackInfo callbackInfo) {
        this.isNenAwakened = false;
        this.nenPower = 0;
        this.nenPowerCap = 0;
        this.nenLvl = 0;
        this.nenExp = 0;
        this.nenType = NenType.UNIDENTIFIED;
        this.nenAbilityPoints = 0;
//        this.nenRestrictions = new ArrayList<>();
//        this.nenAbilities = new ArrayList<>();
//        this.nenMemory = new HashMap<>();

    }
    // method for saving data to NBT.
    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void nen$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){
        nbt.putBoolean("isNenAwakened", isNenAwakened);
        nbt.putLong("nenPower", nenPower);
        nbt.putLong("nenPowerCap", nenPowerCap);
        nbt.putInt("nenLvl", nenLvl);
        nbt.putInt("nenExp", nenExp);
        nbt.putString("nenType", nenType.toNbt());
        nbt.putInt("nenAbilityPoints", nenAbilityPoints);
//        nbt.putString("nenRestrictions", );
//        nbt.putString("nenAbilities", );
//        nbt.putString("nenMemory", );
    }
    // and reading data from NBT.
    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void nen$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci){
        this.isNenAwakened = nbt.getBoolean("isNenAwakened");
        this.nenPower = nbt.getLong("nenPower");
        this.nenPowerCap = nbt.getLong("nenPowerCap");
        this.nenLvl = nbt.getInt("nenLvl");
        this.nenExp = nbt.getInt("nenExp");
        this.nenType = nenType.fromNbt(nbt.getString("nenType"));
        this.nenAbilityPoints = nbt.getInt("nenAbilityPoints");
//        this.nenRestrictions =
//        this.nenAbilities =
//        this.nenMemory =

    }
    // it's returns boolean value, if false - it's a signal to caller of method,
    // that caster hasn't enough nen, if true - everything is good.
    public boolean nen$collectNen(long value) {
        if ((this.nenPower - value) < 0){
            return false;
        }
        else {
            this.nenPower -= value;
            return true;
        }
    }




    public void nen$giveNen(long value) {
        long power = this.nenPower + value;
        if (power > this.nenPowerCap) {
            this.nenPower = this.nenPowerCap;
        }
        else {
            this.nenPower = power;
        }

    }


    public boolean nen$getIsNenAwakened() {
        return isNenAwakened;
    }

    public void nen$setIsNenAwakened(boolean isNenAwakened) {
        this.isNenAwakened = isNenAwakened;
    }

    public int nen$getNenAbilityPoints() {
        return nenAbilityPoints;
    }

    public void nen$setNenAbilityPoints(int nenAbilityPoints) {
        this.nenAbilityPoints = nenAbilityPoints;
    }

    public NenType nen$getNenType() {
        return nenType;
    }

    public void nen$setNenType(NenType nenType) {
        this.nenType = nenType;
    }


    public int nen$getNenLvl() {
        return nenLvl;
    }

    public void nen$setNenLvl(int nenLvl) {
        this.nenLvl = nenLvl;
    }


    public long nen$getNenPowerCap() {
        return nenPowerCap;
    }

    public void nen$setNenPowerCap(long nenPowerCap) {
        this.nenPowerCap = nenPowerCap;
    }


    public long nen$getNenPower() {
        return nenPower;
    }

    public void nen$setNenPower(long nenPower) {
        this.nenPower = nenPower;
    }

    public int nen$getNenExp() {
        return nenExp;
    }

    public void nen$setNenExp(int nenExp) {
        this.nenExp = nenExp;
    }


    public ArrayList<Restriction> nen$getNenRestrictions() {
        return nenRestrictions;
    }

    public void nen$setNenRestrictions(ArrayList<Restriction> nenRestrictions) {
        this.nenRestrictions = nenRestrictions;
    }


    public ArrayList<Ability> nen$getNenAbilities() {
        return nenAbilities;
    }

    public void nen$setNenAbilities(ArrayList<Ability> nenAbilities) {
        this.nenAbilities = nenAbilities;
    }




}
