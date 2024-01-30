package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.register.registry.EffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Scanner;

public abstract class AbilityEffect implements CanRegister {
    protected double nenPower;
    protected PlayerEntity caster;
    protected String id;
    protected int duration;
    protected boolean isFirstTick = true;
    protected boolean isBuff;
    protected boolean isOneTimeApplied;
    public void prepareEffect(PlayerEntity caster, double nenPower){
        this.nenPower = nenPower;
        this.caster = caster;
        // need to realize Entity mixin
    }
    public void applyEffectOnBlock(BlockPos target, PlayerEntity caster, double nenPower){
        this.nenPower = nenPower;
        this.caster = caster;
        firstTickEffect(target);
        // as we can't apply ability effect on blocks - we don't switch isFirstTick, anyway, it doesn't matter.
    }
    public void removeEffect(Entity target){
        onRemoveEffect(target);
    }
    protected abstract void firstTickEffect(Entity target);

    protected abstract void firstTickEffect(BlockPos target);

    public void tickableEffect(Entity target){
        if (isFirstTick){
            firstTickEffect(target);
            isFirstTick = false;
        }
        else if (!isOneTimeApplied){
            durationalEffect(target);
        }
    }
    protected abstract void durationalEffect(Entity target);
    protected abstract void onRemoveEffect(Entity target);

    public static String toNbt(AbilityEffect effect){
        return effect.id + " " + effect.duration + " " + effect.isFirstTick + " " + effect.nenPower;
    }

    public static AbilityEffect fromNbt(String id){
        Scanner scanner = new Scanner(id);
        scanner.useDelimiter(" ");
        String effectId = scanner.next();
        int duration = Integer.parseInt(scanner.next());
        boolean isFirstTick = Boolean.parseBoolean(scanner.next());
        long nenPower = Long.parseLong(scanner.next());

        AbilityEffect effect = EffectRegistry.getInstance().getFromRegistry(effectId);
        effect.duration = duration;
        effect.isFirstTick = isFirstTick;
        effect.nenPower = nenPower;

        return effect;
    }


    @Override
    public final void register() {
        EffectRegistry.getInstance().addToRegistry(id, this);
    }

// Logic here is:
// if duration is more or equals, than 1 - we decrease it by one,
// if == 0, then method returns false, and we remove effect, if duration == -1, this effect can't expire at all.
    public boolean calcDuration()
    {
        if (this.duration == 0) {
            return false;
        } else if (this.duration == -1) {
            return true;
        }
        else {
            this.duration -= 1;
            return true;
        }
    }
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }

}
