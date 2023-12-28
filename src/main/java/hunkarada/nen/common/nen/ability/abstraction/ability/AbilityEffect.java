package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.IEntityNen;
import hunkarada.nen.common.register.registry.EffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Scanner;

public abstract class AbilityEffect implements CanRegister {
    PlayerEntity caster;
    protected String id;
    protected int duration;
    protected boolean isFirstTick = true;

    public void applyEffect(Entity target, PlayerEntity caster){
        this.caster = caster;
        firstTickEffect(target);
        isFirstTick = false;
        if (duration != 0){
            // need to realize Entity mixin
            IEntityNen nenTarget = (IEntityNen) target;
            nenTarget.nen$addNenAbilityEffect(this, caster);
        }


    }
    public void applyEffect(BlockPos target, PlayerEntity caster){
        this.caster = caster;

    }
    protected abstract void firstTickEffect(Entity target);
    public abstract void durationalEffect(Entity target);

    public static String toNbt(AbilityEffect effect){
        return effect.id + " " + effect.duration + " " + effect.isFirstTick;
    }

    public static AbilityEffect fromNbt(String id){
        Scanner scanner = new Scanner(id);
        scanner.useDelimiter(" ");
        String effectId = scanner.next();
        int duration = Integer.parseInt(scanner.next());
        boolean isFirstTick = Boolean.parseBoolean(scanner.next());

        AbilityEffect effect = EffectRegistry.getInstance().getFromRegistry(effectId);
        effect.duration = duration;
        effect.isFirstTick = isFirstTick;

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

}
