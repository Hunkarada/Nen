package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.ability.abilities.EmptyAbility;

import java.util.ArrayList;

public class AbilitySet {
    protected ArrayList<Ability> abilityList = new ArrayList<>(6);
    public AbilitySet() {
    }

    public static AbilitySet generateEmptySet(){
        AbilitySet abilitySet = new AbilitySet();
        abilitySet.abilityList.add(new EmptyAbility());
        abilitySet.abilityList.add(new EmptyAbility());
        abilitySet.abilityList.add(new EmptyAbility());
        abilitySet.abilityList.add(new EmptyAbility());
        abilitySet.abilityList.add(new EmptyAbility());
        abilitySet.abilityList.add(new EmptyAbility());
        return abilitySet;
    }
    public ArrayList<Ability> getAbilitySet(){
        return abilityList;
    }
    public void setAbilityList(ArrayList<Ability> abilities){
        this.abilityList = abilities;
    }
    public void calcAbilityCooldowns(){
        for (Ability ability : abilityList){
            ability.calcCooldown();
        }
    }
}
