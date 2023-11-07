package hunkarada.nen.common.nen.ability.abstraction.ability;


import hunkarada.nen.common.nen.NenType;
import net.minecraft.entity.LivingEntity;

// Class for creating other abilities
public abstract class Ability  {
    // cost in nen for that ability.
    protected int nenPower;
    protected String id;
    protected int staticCost;
    protected float dynamicCostPercent;
    protected NenType nenType;
    protected int cooldown;

    protected abstract int calcNenPower(LivingEntity caster);

    public abstract void cast(LivingEntity caster);

    protected abstract <T> void applyAbilityEffect(T target);

    public String getId() {
        return id;
    }
}
