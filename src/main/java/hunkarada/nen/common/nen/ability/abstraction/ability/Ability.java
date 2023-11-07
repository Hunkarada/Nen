package hunkarada.nen.common.nen.ability.abstraction.ability;


import hunkarada.nen.common.abstractions.nbt.CanNbt;
import hunkarada.nen.common.nen.NenType;
import net.minecraft.entity.LivingEntity;

// Class for creating other abilities
public abstract class Ability implements CanNbt {
    // cost in nen for that ability.
    protected int totalCost;
    protected String id;
    protected int staticCost;
    protected float dynamicCostPercent;
    protected NenType nenType;
    protected int cooldown;
    protected AbilityEffect abilityEffect;

    protected abstract int calcNenPower(LivingEntity caster);

    public abstract void cast(LivingEntity caster);

    public String getId() {
        return id;
    }
}
