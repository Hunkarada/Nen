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
    @Shadow private float movementSpeed;

    public LivingEntityNen(EntityType<?> type, World world) {
        super(type, world);
    }
    @ModifyVariable(method = "getMovementSpeed()F", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public LivingEntity applyMovementMultiplierNen(LivingEntity value){
        if ((LivingEntity)(Object)this instanceof PlayerEntity){
            IPlayerEntityNen nenPlayer = (IPlayerEntityNen) this;
            if (nenPlayer.nen$getIsNenAwakened() && nenPlayer.nen$getIsNenActive()) {
                return value.movementSpeed * nenPlayer.nen$getActiveSpeedMultiplier();
            } else if (nenPlayer.nen$getIsNenAwakened()) {
                return value.movementSpeed * nenPlayer.nen$getPassiveSpeedMultiplier();
            }
            else {
                return movementSpeed;
            }
        }
        return movementSpeed;
    }
}
