package hunkarada.nen.common.nen.abilities.abstractions.abilities;


import hunkarada.nen.common.nen.NenType;
import net.minecraft.entity.LivingEntity;

// Class for creating other abilities
public abstract class Ability  {
    // cost in nen for that ability.
    protected int nenPower;
    protected String id;
    protected NenType nenType;

    public abstract void cast(LivingEntity caster);

    protected abstract <T> void applyAbilityEffect(T target);

    public String getId() {
        return id;
    }
}
