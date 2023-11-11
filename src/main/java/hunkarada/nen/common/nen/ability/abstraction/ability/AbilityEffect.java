package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanNbt;
import net.minecraft.entity.LivingEntity;

public abstract class AbilityEffect <Target> implements CanNbt {
    protected LivingEntity caster;
    protected Target target;
    protected String id;
    protected int duration;
    protected boolean isFirstTick;
    protected <T> void effect(T target){
        this.target = (Target) target;
    }

    @Override
    public String toNbt(){
        return id;
    }

    @Override
    public AbilityEffect fromNbt(String nbt){
       switch (nbt){
           case "" -> {

           }
       }
    }

}
