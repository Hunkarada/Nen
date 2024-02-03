package hunkarada.nen.common.nen.mixin;

import com.mojang.authlib.GameProfile;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.ability.abilities.EmptyNenClass;
import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenClass;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenClassSet;
import hunkarada.nen.common.nen.restriction.Restriction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

// This class is mixing into every LivingEntity, and this adds Nen data to all living creatures in minecraft.
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityNen
        extends LivingEntity
        implements IPlayerEntityNen {

    @Unique
    boolean isNenAwakened;
    @Unique
    double nenPower;
    // nen power cap is the maximum capacity of nen user, nen power is the current amount on nen
    @Unique
    double nenPowerCap;
    // nen level is current level of nen user. more lvl - more nenCap and more ability points.
    @Unique
    int nenLvl;
    @Unique
    long nenExp;
    @Unique
    long nenExpToNextLvl;
    // user's Type of nen, if you are a manipulator - you can use manipulation at 100% effectiveness. See ./src/resources/nen_types_explanation.jpg for details.
    @Unique
    NenType nenType;
    // restrictions are something that restricts you and punish you if you won't follow restriction,
    // but it gives you more nen.
    @Unique
    ArrayList<Restriction> nenRestrictions;
    // list of available abilities to select.
    @Unique
    NenClassSet nenUnlockedClasses;
    // abilities, which caster can use.
    @Unique
    AbilitySet nenAbilities;
    @Unique
    NenClass nenClass;
    @Unique
    double nenRegenValue;
    @Unique
    boolean isNenActive;
    @Unique
    boolean isNenBlocked;
    @Unique
    boolean isNenHidden;
    @Unique
    float passiveResist;
    @Unique
    float activeResist;
    @Unique
    float passiveDamageMultiplier;
    @Unique
    float activeDamageMultiplier;
    @Unique
    float passiveSpeedMultiplier;
    @Unique
    float activeSpeedMultiplier;

    protected PlayerEntityNen(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    //here we are add variables for each entity.
    @Inject(method = "<init>", at = @At("TAIL"))
    public void LivingEntityNen(World world, BlockPos pos, float yaw, GameProfile gameProfile, CallbackInfo ci) {
        this.isNenAwakened = false;
        this.nenPower = 0;
        this.nenPowerCap = 0;
        this.nenLvl = 0;
        this.nenExp = 0;
        this.nenExpToNextLvl = 100;
        this.nenType = NenType.UNIDENTIFIED;
//        this.nenRestrictions = new ArrayList<>();
        this.nenAbilities = AbilitySet.generateEmptySet();
        this.nenUnlockedClasses = new NenClassSet();
        this.nenClass = new EmptyNenClass();
        this.nenRegenValue = 0;
    }
    public NbtCompound nen$saveDataToNbt(){
        NbtCompound nbt = new NbtCompound();
        nbt.putBoolean("isNenAwakened", isNenAwakened);
        nbt.putDouble("nenPower", nenPower);
        nbt.putDouble("nenPowerCap", nenPowerCap);
        nbt.putInt("nenLvl", nenLvl);
        nbt.putLong("nenExp", nenExp);
        nbt.putLong("nenExpUntilNextLvl", nenExpToNextLvl);
        nbt.putString("nenType", NenType.toNbt(nenType));
//        nbt.putString("nenRestrictions", );
        nbt.put("nenAbilities", AbilitySet.toNbt(nenAbilities));
        nbt.put("nenUnlockedClasses", NenClassSet.toNbt(nenUnlockedClasses));
        nbt.putString("nenClass", NenClass.toNbt(nenClass));
        nbt.putDouble("nenRegenValue", nenRegenValue);
        return nbt;
    }
    public void nen$loadDataFromNbtDisk(NbtCompound nbt){
        NbtCompound nen = nbt.getCompound("nen");
        nen$loadDataFromNbt(nen);
    }

    public void nen$loadDataFromNbt(NbtCompound nbt){
        this.isNenAwakened = nbt.getBoolean("isNenAwakened");
        this.nenPower = nbt.getDouble("nenPower");
        this.nenPowerCap = nbt.getDouble("nenPowerCap");
        this.nenLvl = nbt.getInt("nenLvl");
        this.nenExp = nbt.getLong("nenExp");
        this.nenExpToNextLvl = nbt.getLong("nenExpUntilNextLvl");
        this.nenType = NenType.fromNbt(nbt.getString("nenType"));
//        this.nenRestrictions =
        this.nenAbilities = AbilitySet.fromNbt(nbt.getCompound("nenAbilities"));
        this.nenUnlockedClasses = NenClassSet.fromNbt(nbt.getCompound("nenUnlockedClasses"));
        this.nenClass = NenClass.fromNbt(nbt.getString("nenClass"));
        this.nenRegenValue = nbt.getDouble("nenRegenValue");
    }

    // method for saving data to NBT.
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void nen$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.put("nen", nen$saveDataToNbt());
    }

    // and reading data from NBT.
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void nen$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        nen$loadDataFromNbtDisk(nbt);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void nen$tick(CallbackInfo ci) {
        nenAbilities.calcAbilityCooldowns();
    }

    // it's returns boolean value, if false - it's a signal to caller of method,
    // that caster hasn't enough nen, if true - everything is good.
    public boolean nen$collectNen(double value) {
        if ((this.nenPower - value) < 0) {
            return false;
        } else {
            this.nenPower -= value;
            return true;
        }
    }


    public void nen$giveNen(double value) {
        double power = this.nenPower + value;
        if (power > this.nenPowerCap) {
            this.nenPower = this.nenPowerCap;
        } else {
            this.nenPower = power;
        }

    }

    public boolean nen$getIsNenAwakened() {
        return isNenAwakened;
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

    public void nen$addNenLvl() {
        this.nenLvl += 1;
    }

    public double nen$getNenPowerCap() {
        return nenPowerCap;
    }

    public double nen$getNenPower() {
        return nenPower;
    }

    public long nen$getNenExp() {
        return nenExp;
    }

    public void nen$addNenExp(int exp) {
        this.nenExp += exp;
    }

    public ArrayList<Restriction> nen$getNenRestrictions() {
        return nenRestrictions;
    }

    public AbilitySet nen$getNenAbilities() {
        return nenAbilities;
    }

    public NenClassSet nen$getNenUnlockedClasses() {
        return nenUnlockedClasses;
    }

    public NenClass nen$getNenClass() {
        return nenClass;
    }

    public long nen$getNenExpUntilNextLvl() {
        return nenExpToNextLvl;
    }

    public void nen$awakePlayer() {
        this.nenLvl = 1;
        this.nenPowerCap = 1000;
        this.nenExpToNextLvl = 100;
        this.nenType = NenType.randomType();
        this.nenRegenValue = 0.05;
    }

    // add, remove and swap abilities in hotbar, this is not available abilities.
    public void nen$addAbility(Ability ability, int index) {
        if (nenUnlockedClasses.contains(ability)) {
            this.nenAbilities.addAbility(ability, index);
        }
    }

    public void nen$removeAbility(Ability ability) {
        this.nenAbilities.removeAbility(ability);
    }

    public void nen$swapAbilities(Ability firstAbility, Ability secondAbility) {
        this.nenAbilities.swapAbilities(firstAbility, secondAbility);
    }

    public void nen$addExp(long nenExp) {
        this.nenExp += nenExp;
        // method is checking and lvl up player, if true. if false - breaks loop.
        while (true) {
            if (!nen$checkLvlUp()) {
                break;
            }
        }
    }

    public boolean nen$checkLvlUp() {
        if (nenExp >= nenExpToNextLvl) {
            nenExp -= nenExpToNextLvl;
            nen$lvlUp();
            return true;
        } else {
            return false;
        }
    }

    public void nen$lvlUp() {
        nenLvl += 1;
        nenPowerCap += 100;
        nenExpToNextLvl += (long) (nenExpToNextLvl * 0.1);
        nenRegenValue += 0.0025;
    }

    public void nen$regenNen() {
        if (getMaxHealth() == getHealth()) {
            nenPower += nenRegenValue;
            if (nenPower > nenPowerCap) {
                nenPower = nenPowerCap;
            }
        }
    }

    public void nen$setNenClass(NenClass nenClass){
        this.nenClass = nenClass;
//        applyNenResistsAndModifiers(nenClass.);
    }
    public void applyNenResistsAndModifiers(double activeResist, double passiveResist, double activeDamageMultiplier, double passiveDamageMultiplier){
    }
    @Inject(method = "damage", at = @At("TAIL"))
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (isNenAwakened) {
            if (!source.isIn(DamageTypeTags.BYPASSES_ARMOR) || source.isIn(DamageTypeTags.IS_FALL)) {
                if (isNenActive) {
                    amount = amount * activeResist;
                } else {
                    amount = amount * passiveResist;
                }
            }
        }
    }
    @ModifyVariable(method = "attack", at = @At("LOAD"), ordinal = 0)
    public float attack(float f){
        if (isNenAwakened){
            if (isNenActive){
                return f * activeDamageMultiplier;
            }
            else {
                return f * passiveDamageMultiplier;
            }
        }
        else {
            return f;
        }
    }
}