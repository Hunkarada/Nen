package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.ability.abstraction.target.TargetType;
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
    public void cast(LivingEntity caster, long cost) {
        caster.collectNen();
        getTargetByLookVector(caster);
        switch (target.getType()){
            case BLOCK -> {
                if (targetType == TargetType.BLOCK || targetType == TargetType.MIXED){
                    World world = caster.getWorld();
                    BlockHitResult blockTarget = (BlockHitResult) target;
                    abilityEffect.effect(
                            world.getBlockState(blockTarget.getBlockPos()).getBlock(), blockTarget.getType()
                    );
                }
                else {
                    refundNen(caster);
                }
            }
            case ENTITY -> {
                if (targetType == TargetType.ENTITY || targetType == TargetType.MIXED){
                    World world = caster.getWorld();
                    EntityHitResult entityTarget = (EntityHitResult) target;
                    abilityEffect.effect(
                            ((EntityHitResult) target).getEntity(), entityTarget.getType()
                    );
                }
                else {
                    refundNen(caster);
                }

            }
            case MISS -> {
                refundNen(caster);
                //Notify about fail here.
            }
        }
    }

    protected void getTargetByLookVector(LivingEntity caster) {
        this.target = caster.raycast(spellDistance, 1.0f, false);
    }
}
