package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.register.registry.EffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Scanner;

public abstract class AbilityEffect implements CanRegister {
    private double nenPower;
    private PlayerEntity caster;
    protected String id;

    // if you want to instant ability - set duration to 1 (it won't be instant, but will cast on the next tick);
    private int duration;
    private boolean isInstant;
    private boolean isBuff;

    public void prepareEffect(PlayerEntity caster, double nenPower){
        this.nenPower = nenPower;
        this.caster = caster;
        // need to realize Entity mixin
    }
    public void applyEffectOnBlock(BlockPos target, PlayerEntity caster, double nenPower){
        this.nenPower = nenPower;
        this.caster = caster;
        instantEffect(target);
    }
    public void applyInstantEffectOnEntity(Entity target, PlayerEntity caster, double nenPower){
        this.nenPower = nenPower;
        this.caster = caster;
        instantEffect(target);
    }
    public void removeEffect(Entity target){
        onRemoveEffect(target);
    }
    protected abstract void instantEffect(Entity target);

    protected abstract void instantEffect(BlockPos target);

    public void tickEffect(Entity target){
        durationalEffect(target);
    }
    protected abstract void durationalEffect(Entity target);
    protected abstract void onRemoveEffect(Entity target);

    public static String toNbt(AbilityEffect effect){
        return effect.id + " " + effect.duration + " " + effect.isInstant + " " + effect.nenPower;
    }

    public static AbilityEffect fromNbt(String id){
        Scanner scanner = new Scanner(id);
        scanner.useDelimiter(" ");
        String effectId = scanner.next();
        int duration = Integer.parseInt(scanner.next());
        boolean isInstant = Boolean.parseBoolean(scanner.next());
        long nenPower = Long.parseLong(scanner.next());

        AbilityEffect effect = EffectRegistry.getInstance().getFromRegistry(effectId);
        effect.duration = duration;
        effect.isInstant = isInstant;
        effect.nenPower = nenPower;

        return effect;
    }

    protected void setDuration(int duration){
        this.duration = duration;
    }
    protected void setIsBuff(boolean isBuff){
        this.isBuff = isBuff;
    }
    protected PlayerEntity getCaster(){
        return caster;
    }
    public boolean getIsInstant(){
        return isInstant;
    }
    protected void setIsInstant(boolean isInstant){
        this.isInstant = isInstant;
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
