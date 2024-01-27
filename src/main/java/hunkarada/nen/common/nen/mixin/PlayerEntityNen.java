package hunkarada.nen.common.nen.mixin;

import com.mojang.authlib.GameProfile;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.ability.abilities.EmptyNenClass;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenClass;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenClassSet;
import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilitySet;
import hunkarada.nen.common.nen.restriction.Restriction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

// This class is mixing into every LivingEntity, and this adds Nen data to all living creatures in minecraft.
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityNen
        extends LivingEntity
        implements IPlayerEntityNen {

    @Shadow public abstract void tick();

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

    protected PlayerEntityNen(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    //here we are add variables for each entity.
    @Inject(method = "<init>", at = @At("RETURN"))
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
    }
    // method for saving data to NBT.
    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void nen$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){
        nbt.putBoolean("isNenAwakened", isNenAwakened);
        nbt.putLong("nenPower", nenPower);
        nbt.putLong("nenPowerCap", nenPowerCap);
        nbt.putInt("nenLvl", nenLvl);
        nbt.putLong("nenExp", nenExp);
        nbt.putLong("nenExpUntilNextLvl", nenExpToNextLvl);
        nbt.putString("nenType", NenType.toNbt(nenType));
//        nbt.putString("nenRestrictions", );
        nbt.put("nenAbilities", AbilitySet.toNbt(nenAbilities));
        nbt.put("nenUnlockedClasses", NenClassSet.toNbt(nenUnlockedClasses));
        nbt.putString("nenClass", NenClass.toNbt(nenClass));
    }
    // and reading data from NBT.
    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void nen$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci){
        this.isNenAwakened = nbt.getBoolean("isNenAwakened");
        this.nenPower = nbt.getLong("nenPower");
        this.nenPowerCap = nbt.getLong("nenPowerCap");
        this.nenLvl = nbt.getInt("nenLvl");
        this.nenExp = nbt.getLong("nenExp");
        this.nenExpToNextLvl = nbt.getLong("nenExpUntilNextLvl");
        this.nenType = NenType.fromNbt(nbt);
//        this.nenRestrictions =
        this.nenAbilities = AbilitySet.fromNbt(nbt);
        this.nenUnlockedClasses = NenClassSet.fromNbt(nbt);
        this.nenClass = NenClass.fromNbt(nbt.getString("nenClass"));
    }
    @Inject(method = "tick", at = @At("RETURN"))
    public void nen$tick(CallbackInfo ci){
        nenAbilities.calcAbilityCooldowns();
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
    public NenType nen$getNenType() {
        return nenType;
    }
    public void nen$setNenType(NenType nenType) {
        this.nenType = nenType;
    }
    public int nen$getNenLvl() {
        return nenLvl;
    }
    public void nen$addNenLvl(){
        this.nenLvl += 1;
    }
    public long nen$getNenPowerCap() {
        return nenPowerCap;
    }
    public long nen$getNenPower() {
        return nenPower;
    }
    public long nen$getNenExp() {
        return nenExp;
    }
    public void nen$addNenExp(int exp){
       this.nenExp += exp;
    }
    public ArrayList<Restriction> nen$getNenRestrictions() {
        return nenRestrictions;
    }
    public AbilitySet nen$getNenAbilities() {
        return nenAbilities;
    }
    public NenClassSet nen$getNenUnlockedClasses(){
        return nenUnlockedClasses;
    }
    public NenClass nen$getNenClass(){
        return nenClass;
    }
    public long nen$getNenExpUntilNextLvl() {
        return nenExpToNextLvl;
    }

    public void nen$awakePlayer(){
        this.nenLvl = 1;
        this.nenPowerCap = 1000;
        this.nenExpToNextLvl = 100;
        this.nenType = NenType.randomType();
    }
    public void nen$setDataFromPacket(
            boolean isNenAwakened,
            long nenPower,
            long nenPowerCap,
            int nenLvl,
            long nenExp,
            long nenExpUntilNextLvl,
            NenType nenType,
            AbilitySet nenAbilities,
            NenClassSet nenUnlockedClasses,
            NenClass nenClass){
       this.isNenAwakened = isNenAwakened;
       this.nenPower = nenPower;
       this.nenPowerCap = nenPowerCap;
       this.nenLvl = nenLvl;
       this.nenExp = nenExp;
       this.nenExpToNextLvl = nenExpUntilNextLvl;
       this.nenType = nenType;
       this.nenAbilities = nenAbilities;
       this.nenUnlockedClasses = nenUnlockedClasses;
       this.nenClass = nenClass;
    }
    // add, remove and swap abilities in hotbar, this is not available abilities.
    public void nen$addAbility(Ability ability, int index){
        if (nenUnlockedClasses.contains(ability)) {
            this.nenAbilities.addAbility(ability, index);
        }
    }
    public void nen$removeAbility(Ability ability){
       this.nenAbilities.removeAbility(ability);
    }
    public void nen$swapAbilities(Ability firstAbility, Ability secondAbility){
       this.nenAbilities.swapAbilities(firstAbility, secondAbility);
    }
    public void nen$addExp(long nenExp){
        this.nenExp += nenExp;
        nen$checkLvlUp();
    }
    public void nen$checkLvlUp(){
        if (nenExp >= nenExpToNextLvl){
            nenExp -= nenExpToNextLvl;
            nen$lvlUp();
        }
    }
    public void nen$lvlUp(){
        nenLvl += 1;
        nenPowerCap += 100;
        nenExpToNextLvl += (long) (nenExpToNextLvl*0.1);
    }
    public void nen$regenNen(){
        //we should regen nen in 1000 seconds, in ticks its 1000*20.
        if (getMaxHealth() == getHealth()) {
            nenPower += nenPowerCap / 20000;
        }
    }
}
