package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.ability.abstraction.target.TargetType;
import hunkarada.nen.common.nen.mixin.ILivingEntityNen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public abstract class TargetAbility extends Ability {

    TargetType targetType;
    HitResult target;
    double spellDistance;

    @Override
    public void cast(LivingEntity caster) {
        if (isNotAtCooldown()){
            // calculate cost and other things
            prepareCast(caster);
            // took away nen from caster with check.
            ILivingEntityNen nenCaster = (ILivingEntityNen) caster;
            if (nenCaster.nen$collectNen(totalCost)){
                switch (target.getType()){
                    case BLOCK -> {
                        if (targetType == TargetType.BLOCK || targetType == TargetType.MIXED){
                            World world = caster.getWorld();
                            BlockHitResult blockTarget = (BlockHitResult) target;
                            abilityEffect.applyEffect(
                                    world.getBlockState(blockTarget.getBlockPos()).getBlock(), caster
                            );
                        }
                        else {
                            nenCaster.nen$giveNen(totalCost);
                        }
                    }
                    case ENTITY -> {
                        if (targetType == TargetType.ENTITY || targetType == TargetType.MIXED){
                            EntityHitResult entityTarget = (EntityHitResult) target;
                            abilityEffect.applyEffect(
                                    ((EntityHitResult) target).getEntity(), caster
                            );
                        }
                        else {
                            nenCaster.nen$giveNen(totalCost);
                        }

                    }
                    case MISS -> nenCaster.nen$giveNen(totalCost);
                }
            }
            // getting target
            getTargetByLookVector(caster);

        }
    }

    protected void getTargetByLookVector(LivingEntity caster) {
        this.target = caster.raycast(spellDistance, 1.0f, false);
    }
}
