package hunkarada.nen.common.nen.mixin;

import hunkarada.nen.common.nen.AuraType;
import hunkarada.nen.common.nen.INen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// This class is mixing into every LivingEntity, and this adds Nen data to all living creatures in minecraft.
@Mixin(LivingEntity.class)
public abstract class LivingEntityNen
        extends Entity
        implements INen {

    boolean isAwakened;
    int power;
    int powerCap;
    int nenLvl;
    int nenExp;
    AuraType auraType;
    int abilityPoints;

    public LivingEntityNen(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void LivingEntityNen(EntityType<?> type, World world, CallbackInfo callbackInfo) {
        this.isAwakened = false;
        this.power = 0;
        this.powerCap = 0;
        this.nenLvl = 0;
        this.nenExp = 0;
        this.auraType = AuraType.UNIDENTIFIED;
        this.abilityPoints = 0;
    }

    @Override
    public boolean getIsAwakened() {
        return isAwakened;
    }
    public void setIsAwakened(boolean isAwakened){
       this.isAwakened = isAwakened;
    }

    public int getAbilityPoints() {
        return abilityPoints;
    }

    public void setAbilityPoints(int abilityPoints) {
        this.abilityPoints = abilityPoints;
    }

    public AuraType getAuraType() {
        return auraType;
    }

    public void setAuraType(AuraType auraType) {
        this.auraType = auraType;
    }


    public int getNenLvl() {
        return nenLvl;
    }

    public void setNenLvl(int nenLvl) {
        this.nenLvl = nenLvl;
    }


    public int getPowerCap() {
        return powerCap;
    }

    public void setPowerCap(int powerCap) {
        this.powerCap = powerCap;
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getNenExp() {
        return nenExp;
    }

    public void setNenExp(int nenExp) {
        this.nenExp = nenExp;
    }
}
