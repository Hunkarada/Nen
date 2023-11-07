package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.abstractions.nbt.CanNbt;

public abstract class AbilityEffect implements CanNbt {
    protected String id;
    protected int duration;
    protected boolean isFirstTick;
    protected abstract <T> void effect(T target);

    @Override
    public String toNbt(){
        return id;
    }

    @Override
    public AbilityEffect fromNbt(String nbt){
       switch (nbt){
           case "" -> {

           }
       }
    }

}
