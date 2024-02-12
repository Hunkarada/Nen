package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.IEntityNen;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenAbilityEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public abstract class TargetAbility extends Ability {
    protected NenAbilityEntity visualEntity;
    protected TargetType targetType;
    private HitResult target;
    protected double spellDistance;

    @Override
    public void cast(PlayerEntity caster) {
        // took away nen from caster with check.
        IPlayerEntityNen nenCaster = (IPlayerEntityNen) caster;
        if (isNotAtCooldown() && nenCaster.nen$getIsNenAwakened() && nenCaster.nen$getIsNenActive()){
            // getting target
            getTargetByLookVector(caster);
            // calculate cost and other things
            prepareCast(caster);
            if (nenCaster.nen$collectNen(this.getTotalCost())){
                switch (target.getType()){
                    case BLOCK -> {
                        if (targetType == TargetType.BLOCK || targetType == TargetType.MIXED){
                            BlockHitResult blockTarget = (BlockHitResult) target;
                            abilityEffect.applyEffectOnBlock(
                                    blockTarget.getBlockPos(), caster, getNenPower()
                            );
                            spawnVisualEntities();
                            setInitialCooldown();
                        }
                        else {
                            nenCaster.nen$giveNen(this.getTotalCost());
                        }
                    }
                    case ENTITY -> {
                        if (targetType == TargetType.ENTITY || targetType == TargetType.MIXED){
                            EntityHitResult entity = (EntityHitResult) target;
                            IEntityNen entityNen = (IEntityNen) entity;
                            entityNen.nen$applyNenAbilityEffect(abilityEffect, caster, getNenPower());
                            spawnVisualEntities();
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

    protected abstract void spawnVisualEntities();
    protected abstract void spawnVisualEntity(Vec3d pos);
    protected abstract Vec3d calcVisualEntityPos();
}
