package hunkarada.nen.common.nen.ability.abstraction.ability;


import hunkarada.nen.common.abstractions.CanNbt;
import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.ability.registry.AbilityRegistry;
import hunkarada.nen.common.nen.mixin.INen;
import net.minecraft.entity.LivingEntity;

// Class for creating other abilities
public abstract class Ability implements CanNbt, CanRegister {
    // cost in nen for that ability.

    //the difference between totalCost and nenPower is totalCost -
    // how much nen we get from player, nenPower -
    // how actually powerful ability is (after calc NenType of caster and ability,
    // or after applying restriction multipliers).
    protected LivingEntity caster;
    protected long totalCost;
    protected long nenPower;
    protected String id;
    protected long staticCost;
    protected float dynamicCostPercent;
    protected NenType nenType;
    protected int cooldown;
    protected AbilityEffect abilityEffect;

    protected void calcNenPower(LivingEntity caster){
        // nenPower = totalCost * ;
    }

    public abstract void cast(LivingEntity caster, long cost);

    public String toNbt(){
        return id;
    }

    public Ability fromNbt(String id){
        return AbilityRegistry.getInstance().getFromRegistry(id);
    }

    public void calcNenCost() {
        INen caster = (INen) this.caster;

        totalCost = (long) Math.round(this.staticCost + caster.nen$getNenPowerCap() * this.dynamicCostPercent);
    }

    @Override
    public void register() {
        AbilityRegistry.getInstance().addToRegistry(id, this);
    }
}
