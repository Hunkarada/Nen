package hunkarada.nen.common.nen.ability.abstraction.ability;

import java.util.ArrayList;

public class AbilitySet {
    protected ArrayList<Ability> abilityList = new ArrayList<>(6);
    public AbilitySet() {
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
