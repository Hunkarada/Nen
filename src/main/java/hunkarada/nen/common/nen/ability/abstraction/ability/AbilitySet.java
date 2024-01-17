package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.ability.abilities.EmptyAbility;
import hunkarada.nen.common.register.registry.AbilityRegistry;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AbilitySet {
    protected ArrayList<Ability> abilitySet = new ArrayList<>();
    protected ArrayList<String> abilityIds = getAbilityIds();
    protected HashMap<String, Ability> abilityMap = getAbilityMap();
    public AbilitySet() {
        prepareSet();
    }

    protected void prepareSet(){
        abilityIds = getAbilityIds();
        abilityMap = getAbilityMap();
    }
    public static AbilitySet generateEmptySet(){
        AbilitySet abilitySet = new AbilitySet();
        abilitySet.addAbilityInsteadOfEmptyAbility(new EmptyAbility()); abilitySet.addAbilityInsteadOfEmptyAbility(new EmptyAbility()); abilitySet.addAbilityInsteadOfEmptyAbility(new EmptyAbility()); abilitySet.addAbilityInsteadOfEmptyAbility(new EmptyAbility()); abilitySet.addAbilityInsteadOfEmptyAbility(new EmptyAbility());
        return abilitySet;
    }
    public ArrayList<Ability> getAbilitySetCopy(){
        return abilitySet;
    }
    public ArrayList<String> getAbilityIds(){
        ArrayList<String> abilityIds = new ArrayList<>();
        for (Ability ability: abilitySet){
            abilityIds.add(ability.getId());
        }
        return abilityIds;
    }

    public HashMap<String, Ability> getAbilityMap(){
        HashMap<String, Ability> map = new HashMap<>();
        for (Ability ability : abilitySet){
            map.put(ability.getId(), ability);
        }
        return map;
    }

    public void calcAbilityCooldowns(){
        for (Ability ability : abilitySet){
            ability.calcCooldown();
        }
    }
    public void addAbility(Ability ability, int index){
       if (!addAbilityInsteadOfEmptyAbility(ability)){
          if (!abilitySet.contains(ability) && index >= 0 && index <= 3){
              abilitySet.set(index, ability);
          }
       }
    }
    // THIS SHOULD WORK, BECAUSE ABILITY HAS OVERRIDDEN equals(Object obj). returns true if success.
    private boolean addAbilityInsteadOfEmptyAbility(Ability ability){
        if (!abilitySet.contains(ability) && abilitySet.contains(new EmptyAbility())) {
            abilitySet.add(ability);
            abilityIds = getAbilityIds();
            abilityMap = getAbilityMap();
            return true;
        }
        else {
            return false;
        }
    }

    public void removeAbility(Ability ability){
        if (abilitySet.contains(ability)) {
            abilitySet.indexOf(ability);
            abilityIds = getAbilityIds();
            abilityMap = getAbilityMap();
        }
    }

    public void swapAbilities(int firstIndex, int secondIndex){
        Ability firstAbility = abilitySet.get(firstIndex);
        Ability secondAbility = abilitySet.get(secondIndex);
        abilitySet.set(secondIndex, firstAbility);
        abilitySet.set(firstIndex, secondAbility);
    }

    public static NbtCompound toNbt(AbilitySet nenAbilitySet){
        ArrayList<Ability> abilities = nenAbilitySet.abilitySet;
        NbtCompound nbt = new NbtCompound();
        for (Ability ability : abilities){
            nbt.putString(ability.getId(), ability.getId());
        }
       return nbt;
    }

    public static AbilitySet fromNbt(NbtCompound nbt){
        NbtCompound nbtAbilities = nbt.getCompound("nenAbilities");
        AbilitySet abilitySet = new AbilitySet();
        Set<String> keys = nbtAbilities.getKeys();
        for (String key : keys){
           abilitySet.addAbilityInsteadOfEmptyAbility(AbilityRegistry.getInstance().getFromRegistry(nbtAbilities.getString(key)));
        }
        return abilitySet;
    }
}
