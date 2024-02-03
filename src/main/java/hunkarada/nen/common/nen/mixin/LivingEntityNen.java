package hunkarada.nen.common.nen.mixin;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityNen extends Entity {

    public LivingEntityNen(EntityType<?> type, World world) {
        super(type, world);
    }
    @Shadow private float movementSpeed;

    @Shadow public abstract void setMovementSpeed(float movementSpeed);

    @ModifyVariable(method = "getMovementSpeed()F", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public LivingEntity applyMovementMultiplierNen(LivingEntity value){
        if (value instanceof PlayerEntity){
            IPlayerEntityNen nenPlayer = (IPlayerEntityNen) value;
            float oldMovementSpeed = movementSpeed;
            if (nenPlayer.nen$getIsNenAwakened() && nenPlayer.nen$getIsNenActive()) {
                value.setMovementSpeed(movementSpeed * nenPlayer.nen$getActiveSpeedMultiplier());
                return value;
                setMovementSpeed(oldMovementSpeed);
            } else if (nenPlayer.nen$getIsNenAwakened()) {
                value.setMovementSpeed(movementSpeed * nenPlayer.nen$getPassiveSpeedMultiplier());
                return value;
                value.setMovementSpeed(oldMovementSpeed);
            }
            else {
                return value;
            }
        }
        return value;
    }
}
