package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanNbt;
import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.ability.registry.EffectRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;

public abstract class AbilityEffect implements CanNbt, CanRegister {
    protected LivingEntity caster;
    protected HitResult.Type targetType;
    protected Block blockTarget;
    protected Entity entityTarget;
    protected String id;
    protected int duration;
    protected boolean isFirstTick = true;
    protected <T> void applyEffect(T target, HitResult.Type targetType, LivingEntity caster){
        applyVariables(target, targetType, this.caster);
        if (targetType == HitResult.Type.ENTITY){
            caster.writeToNenMemory();// write info about ability effect to entity/block method.
            firstTickEffect(entityTarget);
            isFirstTick = false;
        }
        else {
            // write info about ability effect to entity/block method.
            firstTickEffect(blockTarget);
            isFirstTick = false;
        }
    }


    protected abstract  <T> void firstTickEffect(T target);
    protected abstract  <T> void durationalEffect(T target);

    protected <T> void applyVariables(T target, HitResult.Type targetType, LivingEntity caster){
        switch (targetType){
            case ENTITY -> {
                this.targetType = targetType;
                this.entityTarget = (Entity) target;
            }
            case BLOCK -> {
                this.targetType = targetType;
                this.blockTarget = (Block) target;
            }
        }
        this.caster = caster;
    }

    @Override
    public String toNbt(){
        return id;
    }

    @Override
    public AbilityEffect fromNbt(String id){
        return EffectRegistry.getInstance().getFromRegistry(id);
    }
    @Override
    public void register() {
        EffectRegistry.getInstance().addToRegistry(id, this);
    }

}
