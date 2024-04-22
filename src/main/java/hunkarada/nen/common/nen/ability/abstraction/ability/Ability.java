package hunkarada.nen.common.nen.ability.abstraction.ability;


import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.nen.IPlayerEntityNen;
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
    protected double staticCost;
    protected double dynamicCostPercent;
    protected int initialCooldown;
    protected AbilityEffect abilityEffect;
    private int cooldown;
    private PlayerEntity caster;
    private double totalCost;
    private double nenPower;

    public String getId() {
        return id;
    }

    public Ability(){
        setupAbility();
    }
    public abstract void setupAbility();

    protected void calcNenPower(){
       // IPlayerEntityNen nenCaster = (IPlayerEntityNen) this.caster;
        // TODO: nenPower = totalCost * eachRestrictionMultiplier from player
        nenPower = totalCost;
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

    public double getTotalCost() {
        calcNenCost();
        return totalCost;
    }

    public double getNenPower(){
        calcNenPower();
        return nenPower;
    }
    public AbilityEffect getAbilityEffect(){
        return abilityEffect;
    }
    public PlayerEntity getCaster(){
        return caster;
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
