package hunkarada.nen.common.nen.ability.abstraction.ability;


import hunkarada.nen.common.abstractions.CanNbt;
import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.ability.registry.AbilityRegistry;
import net.minecraft.entity.LivingEntity;

// Class for creating other abilities
public abstract class Ability implements CanNbt, CanRegister {
    // cost in nen for that ability.

    //difference between totalCost and nenPower is totalCost - how much nen we get from player, nenPower - how much nen is damaging entity.
    protected int totalCost;
    protected int nenPower;
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

    @Override
    public void register() {
        AbilityRegistry.getInstance().addToRegistry(id, this);
    }
}
