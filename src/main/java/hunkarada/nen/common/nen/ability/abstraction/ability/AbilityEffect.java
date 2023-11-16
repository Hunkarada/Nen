package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanNbt;
import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.ability.registry.EffectRegistry;
import hunkarada.nen.common.nen.mixin.ILivingEntityNen;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public abstract class AbilityEffect implements CanNbt, CanRegister {
    protected LivingEntity caster;
    protected String id;
    protected int duration;
    protected boolean isFirstTick = true;
    protected void applyEffect(Entity target, LivingEntity caster){
        prepareEffect(target, caster);
        firstTickEffect(target);
        isFirstTick = false;
        if (duration != 0){
            // need to realize Entity mixin
            ILivingEntityNen nenTarget = (ILivingEntityNen) target;
        }
    }
    protected void applyEffect(Block target, LivingEntity caster){

    }
    protected abstract void firstTickEffect(Block target);
    protected abstract void firstTickEffect(Entity target);
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

    public boolean calcDuration()
    {
        if (this.duration <= 0) {return true;}
        this.duration -= 1;
        return false;
    }

}
