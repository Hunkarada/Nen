package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanNbt;
import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.ability.abstraction.target.TargetType;
import hunkarada.nen.common.nen.ability.registry.EffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;

public abstract class AbilityEffect implements CanNbt, CanRegister {
    protected LivingEntity caster;
    protected U target;
    protected String id;
    protected int duration;
    protected boolean isFirstTick;
    protected <T> void effect(T target, HitResult.Type targetType){
        this.target = (U) target;
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
