package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public abstract class TargetAbility extends Ability {

    protected TargetType targetType;
    private HitResult target;
    protected double spellDistance;

    @Override
    public void cast(PlayerEntity caster) {
        if (isNotAtCooldown()){
            // getting target
            getTargetByLookVector(caster);
            // calculate cost and other things
            prepareCast(caster);
            // took away nen from caster with check.
            IPlayerEntityNen nenCaster = (IPlayerEntityNen) caster;
            if (nenCaster.nen$collectNen(this.getTotalCost())){
                switch (target.getType()){
                    case BLOCK -> {
                        if (targetType == TargetType.BLOCK || targetType == TargetType.MIXED){
                            BlockHitResult blockTarget = (BlockHitResult) target;
                            abilityEffect.applyEffect(
                                    blockTarget.getBlockPos(), caster, getNenPower()
                            );
                            setInitialCooldown();
                        }
                        else {
                            nenCaster.nen$giveNen(this.getTotalCost());
                        }
                    }
                    case ENTITY -> {
                        if (targetType == TargetType.ENTITY || targetType == TargetType.MIXED){
                            abilityEffect.applyEffect(
                                    ((EntityHitResult) target).getEntity(), caster, getNenPower()
                            );
                            setInitialCooldown();
                        }
                        else {
                            nenCaster.nen$giveNen(this.getTotalCost());
                        }

                    }
                    case MISS -> nenCaster.nen$giveNen(this.getTotalCost());
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
