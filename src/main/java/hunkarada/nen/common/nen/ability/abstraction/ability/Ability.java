package hunkarada.nen.common.nen.ability.abstraction.ability;


import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.register.registry.AbilityRegistry;
import net.minecraft.entity.player.PlayerEntity;

// Class for creating other abilities
public abstract class Ability implements CanRegister {
    // cost in nen for that ability.

    //the difference between totalCost and nenPower is totalCost -
    // how much nen we get from player, nenPower -
    // how actually powerful ability is (after calc NenType of caster and ability,
    // or after applying restriction multipliers).

    protected String id;
    protected long staticCost;
    protected double dynamicCostPercent;
    protected NenType nenType;
    protected int initialCooldown;
    protected AbilityEffect abilityEffect;
    private int cooldown;
    private PlayerEntity caster;
    private long totalCost;
    private long nenPower;

    public String getId() {
        return id;
    }

    public Ability(){

    }

    protected void calcNenPower(){
        IPlayerEntityNen nenCaster = (IPlayerEntityNen) this.caster;
        nenPower = Math.round(totalCost * nenType.calcTypeMultiplier(nenCaster.nen$getNenType()));
    }

    public abstract void cast(PlayerEntity caster);
    protected void calcNenCost() {
        IPlayerEntityNen caster = (IPlayerEntityNen) this.caster;
        totalCost = Math.round(this.staticCost + caster.nen$getNenPowerCap() * this.dynamicCostPercent);
    }

    protected void prepareCast(PlayerEntity caster){
        this.caster = caster;
    }

    protected boolean isNotAtCooldown(){
        return cooldown == 0;
    }

    protected void calcCooldown(){
        if (cooldown > 0){
            this.cooldown -= this.cooldown;
        }
    }

    public long getTotalCost() {
        calcNenCost();
        return totalCost;
    }

    public long getNenPower(){
        calcNenPower();
        return nenPower;
    }

    protected void setInitialCooldown(){
        cooldown = initialCooldown;
    }

    @Override
    public void register() {
        AbilityRegistry.getInstance().addToRegistry(id, this);
    }
    // We NEED this to compare abilities, as they all aren't change their behavior at all.
    // So, we can compare them by class.
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }
}
