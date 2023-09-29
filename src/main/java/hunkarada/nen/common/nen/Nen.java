package hunkarada.nen.common.nen;

import hunkarada.nen.common.nen.INen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

// This class is mixing into every LivingEntity, and this adds Nen data to all living creatures in minecraft.
@Mixin(LivingEntity.class)
public abstract class Nen
        extends Entity
        implements INen {
    @Unique
    boolean isAwakened;



    public Nen(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public boolean getIsAwakened() {
        return false;
    }
}
