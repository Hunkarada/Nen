package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.ability.abstraction.target.TargetType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public abstract class TargetAbility extends Ability {

    TargetType targetType;
    HitResult target;
    double spellDistance;

    @Override
    public void cast(LivingEntity caster) {
        collectNen(caster);
        getTargetByLookVector(caster);
        switch (target.getType()){
            case BLOCK -> {
                if (targetType == TargetType.BLOCK || targetType == TargetType.MIXED){
                    World world = caster.getWorld();
                    BlockHitResult blockTarget = (BlockHitResult) target;
                    applyAbilityEffect(
                            world.getBlockState(blockTarget.getBlockPos()).getBlock()
                    );
                }
                else {
                    refundNen(caster);
                }
            }
            case ENTITY -> {

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
