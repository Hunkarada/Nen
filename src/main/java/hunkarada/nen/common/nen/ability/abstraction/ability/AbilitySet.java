package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.ability.abilities.EmptyAbility;
import hunkarada.nen.common.register.registry.AbilityRegistry;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AbilitySet {
    protected ArrayList<Ability> abilitySet = new ArrayList<>(5);
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
        abilitySet.abilitySet.add(new EmptyAbility());
        abilitySet.abilitySet.add(new EmptyAbility());
        abilitySet.abilitySet.add(new EmptyAbility());
        abilitySet.abilitySet.add(new EmptyAbility());
        abilitySet.abilitySet.add(new EmptyAbility());
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
          if (!abilitySet.contains(ability) && index >= 0 && index <= 3){
              // this fixes, that abilitySet is empty at the stage of loading data from disk,
              // and set() faild because of that.
              if (abilitySet.isEmpty()){
                  abilitySet.add(new EmptyAbility());
                  abilitySet.add(new EmptyAbility());
                  abilitySet.add(new EmptyAbility());
                  abilitySet.add(new EmptyAbility());
                  abilitySet.add(new EmptyAbility());
              }
              abilitySet.set(index, ability);
          }
    }
    public void removeAbility(Ability ability){
        if (abilitySet.contains(ability)) {
            abilitySet.set(abilitySet.indexOf(ability), new EmptyAbility());
            abilityIds = getAbilityIds();
            abilityMap = getAbilityMap();
        }
    }

    public void swapAbilities(Ability firstAbility, Ability secondAbility){
        if (abilitySet.contains(firstAbility) && abilitySet.contains(secondAbility)) {
            abilitySet.set(abilitySet.indexOf(secondAbility), firstAbility);
            abilitySet.set(abilitySet.indexOf(firstAbility), secondAbility);
        }
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
        AbilitySet abilitySet = new AbilitySet();
        Set<String> keys = nbt.getKeys();
        int index = 0;
        for (String key : keys){
           abilitySet.addAbility(AbilityRegistry.getInstance().getFromRegistry(nbt.getString(key)), index);
           index += 1;
        }
        return abilitySet;
    }
    public static AbilitySet fromNbtPacket(NbtCompound nbt){
        AbilitySet abilitySet = new AbilitySet();
        Set<String> keys = nbt.getKeys();
        int index = 0;
        for (String key : keys){
            abilitySet.addAbility(AbilityRegistry.getInstance().getFromRegistry(nbt.getString(key)), index);
            index += 1;
        }
        return abilitySet;
    }

    public enum AbilitySetActions {
        ADD(0), REMOVE(1), SWAP(2);

        AbilitySetActions(int ignoredI) {

        }
        public int toInt(){
            return switch (this){
                case ADD -> 0;
                case REMOVE -> 1;
                case SWAP -> 2;
            };
        }
    }
}
