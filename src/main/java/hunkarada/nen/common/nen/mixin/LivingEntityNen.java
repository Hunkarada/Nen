package hunkarada.nen.common.nen.mixin;

import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.INen;
import hunkarada.nen.common.nen.restriction.Restriction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
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
        implements INen {

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
    @Unique
    ArrayList<Ability> nenAbilities;
    // memory is a place where we save information about abilities, for ex. which block should create conjurator?
    @Deprecated
    @Unique
    HashMap<String, String> nenMemory;



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
    public boolean nen$getIsNenAwakened() {
        return isNenAwakened;
    }
    public void nen$setIsNenAwakened(boolean isNenAwakened){
       this.isNenAwakened = isNenAwakened;
    }

    public int nen$getNenAbilityPoints() {
        return nenAbilityPoints;
    }

    public void nen$setNenAbilityPoints(int nenAbilityPoints) {
        this.nenAbilityPoints = nenAbilityPoints;
    }

    public NenType nen$getAuraType() {
        return nenType;
    }

    public void nen$setAuraType(NenType nenType) {
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

    public void nen$collectNen(long Sum) {
        this.nenPower += Sum;
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

    public HashMap<String, String> nen$getNenMemory(){
        return nenMemory;
    }

    public void nen$setNenMemory(HashMap<String, String> nenMemory){
        this.nenMemory = nenMemory;
    }
// END OF GETTERS AND SETTERS
    public void nen$writeToNenMemory(String abilityId, String data){
        this.nenMemory.put(abilityId, data);
    }

    public String nen$readFromNenMemory(String abilityId){
        return this.nenMemory.get(abilityId);
    }
}
