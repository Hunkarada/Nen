package hunkarada.nen.common.nen.ability.abstraction.ability;


import hunkarada.nen.common.abstractions.CanNbt;
import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.ability.registry.AbilityRegistry;
import hunkarada.nen.common.nen.mixin.ILivingEntityNen;
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

    protected void calcNenPower(){
        ILivingEntityNen nenCaster = (ILivingEntityNen) this.caster;
        nenPower = Math.round(totalCost * nenType.calcTypeMultiplier(nenCaster.nen$getNenType()));
    }

    public abstract void cast(LivingEntity caster);

    public String toNbt(){
        return id;
    }

    public Ability fromNbt(String id){
        return AbilityRegistry.getInstance().getFromRegistry(id);
    }

    protected void calcNenCost() {
        ILivingEntityNen caster = (ILivingEntityNen) this.caster;
        totalCost = Math.round(this.staticCost + caster.nen$getNenPowerCap() * this.dynamicCostPercent);
    }

    protected void prepareCast(LivingEntity caster){
        this.caster = caster;
        calcNenCost();
        calcNenPower();
    }

    protected boolean isNotAtCooldown(){
        //look carefully at ==, not =, it's boolean
        return cooldown == 0;
    }

    protected void calcCooldown(){
        if (cooldown != 0){
            this.cooldown -= this.cooldown;
        }
    }

    @Override
    public void register() {
        AbilityRegistry.getInstance().addToRegistry(id, this);
    }
}
