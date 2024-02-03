package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.CanRegister;
import hunkarada.nen.common.register.registry.NenClassRegistry;

import java.util.ArrayList;

public abstract class NenClass implements CanRegister {
    protected String id;
    protected Ability ultimateClassAbility;
    protected ArrayList<Ability> classAvailableAbilities;
    protected float passiveResist;
    protected float activeResist;
    // not more, than 2!
    protected float passiveDamageMultiplier;
    protected float activeDamageMultiplier;
    protected float passiveSpeedMultiplier;
    protected float activeSpeedMultiplier;
    public NenClass(){

    }
    public static String toNbt(NenClass nenClass){
        return nenClass.id;
    }
    public static NenClass fromNbt(String id){
        return NenClassRegistry.getInstance().getFromRegistry(id);
    }
    public ArrayList<Ability> getClassAvailableAbilities() {
        return classAvailableAbilities;
    }
    public String getId(){
        return this.id;
    }
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }
    public void register(){
        NenClassRegistry.getInstance().addToRegistry(id, this);
    }
}
