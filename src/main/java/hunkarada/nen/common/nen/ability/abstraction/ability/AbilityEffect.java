package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanNbt;
import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.ability.abstraction.target.TargetType;
import hunkarada.nen.common.nen.ability.registry.EffectRegistry;
import hunkarada.nen.common.nen.mixin.INen;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public abstract class AbilityEffect implements CanNbt, CanRegister {
    protected LivingEntity caster;
    protected TargetType targetType;
    protected Block blockTarget;
    protected Entity entityTarget;
    protected String id;
    protected int duration;
    protected boolean isFirstTick = true;
    protected void applyEffect(Entity target, LivingEntity caster){
        prepareEffect(target, this.caster);
        INen nenCaster = (INen) caster;
        firstTickEffect(entityTarget);
        isFirstTick = false;
    }
    protected void applyEffect(Block target, LivingEntity caster){

    }
    protected abstract void firstTickEffect(Block target);
    protected abstract void firstTickEffect(Entity target);
    protected abstract void durationalEffect(Block target);
    protected abstract void durationalEffect(Entity target);

    protected <T> void prepareEffect(T target, LivingEntity caster){
        if (target instanceof Entity) {
            this.entityTarget = (Entity) target;
        }
        else if (target instanceof Block) {
            this.blockTarget = (Block) target;
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
