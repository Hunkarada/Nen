package hunkarada.nen.common.nen;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

// This class is mixing into every LivingEntity, and this adds Nen data to all living creatures in minecraft.
@Mixin(LivingEntity.class)
public abstract class LivingEntityNen
        extends Entity
        implements INen {

    boolean isAwakened;
    int power;
    int powerCap;
    int lvl;
    AuraType auraType;
    int abilityPoints;

    public LivingEntityNen(EntityType<?> type, World world) {
        super(type, world);
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


    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
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
}
