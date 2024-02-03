package hunkarada.nen.common.nen.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityNen extends Entity {

    public LivingEntityNen(EntityType<?> type, World world) {
        super(type, world);
    }
    @Shadow private float movementSpeed;

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Unique
    float oldMovementSpeed;

    @ModifyReturnValue(method = "getMovementSpeed()F", at = @At("RETURN"))
    public float applyMovementMultiplierNen(float value){
        if ((LivingEntity) (Object) this instanceof PlayerEntity){
            IPlayerEntityNen nenPlayer = (IPlayerEntityNen) this;
            if (nenPlayer.nen$getIsNenAwakened() && nenPlayer.nen$getIsNenActive()) {
                return movementSpeed * nenPlayer.nen$getActiveSpeedMultiplier();
            } else if (nenPlayer.nen$getIsNenAwakened()) {
                return movementSpeed * nenPlayer.nen$getPassiveSpeedMultiplier();
            }
            else {
                return value;
            }
        }
        return value;
    }
}
