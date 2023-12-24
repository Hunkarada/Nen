package hunkarada.nen.common.register;

import hunkarada.nen.common.nen.ability.conjuration.creator.selectblockability.SelectBlockAbility;
import hunkarada.nen.common.nen.ability.conjuration.creator.selectblockability.SelectBlockAbilityEffect;

public class NenModRegister {

    public static void registerServer(){
        registerCommon();
    }
    public static void registerClient(){
        registerCommon();
    }
    public static void registerCommon(){
        registerAbilities();
        registerEffects();
    }

    public static void registerAbilities(){
        new SelectBlockAbility().register();
    }

    public static void registerEffects(){
        new SelectBlockAbilityEffect().register();
    }
}
