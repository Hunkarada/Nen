package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public abstract class TargetAbility extends Ability {

    TargetType targetType;
    HitResult target;
    double spellDistance;

    @Override
    public void cast(PlayerEntity caster) {
        if (isNotAtCooldown()){
            // getting target
            getTargetByLookVector(caster);
            // calculate cost and other things
            prepareCast(caster);
            // took away nen from caster with check.
            IPlayerEntityNen nenCaster = (IPlayerEntityNen) caster;
            if (nenCaster.nen$collectNen(totalCost)){
                switch (target.getType()){
                    case BLOCK -> {
                        if (targetType == TargetType.BLOCK || targetType == TargetType.MIXED){
                            BlockHitResult blockTarget = (BlockHitResult) target;
                            abilityEffect.applyEffect(
                                    blockTarget.getBlockPos(), caster
                            );
                        }
                        else {
                            nenCaster.nen$giveNen(totalCost);
                        }
                    }
                    case ENTITY -> {
                        if (targetType == TargetType.ENTITY || targetType == TargetType.MIXED){
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


        }
    }

    protected void getTargetByLookVector(LivingEntity caster) {
        this.target = caster.raycast(spellDistance, 1.0f, false);
    }

    public enum TargetType {
        ENTITY, BLOCK, MIXED
    }
}
