package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.IEntityNen;
import hunkarada.nen.common.nen.IPlayerEntityNen;
import net.minecraft.entity.player.PlayerEntity;

public abstract class SelfAbility extends Ability {
    @Override
    public void cast(PlayerEntity caster){
        IPlayerEntityNen nenCaster = (IPlayerEntityNen) caster;
        if (isNotAtCooldown() && nenCaster.nen$getIsNenAwakened() && nenCaster.nen$getIsNenActive()){
            // calculate cost and other things
            prepareCast(caster);
            if (nenCaster.nen$collectNen(this.getTotalCost())){
                IEntityNen target = (IEntityNen) caster;
                target.nen$applyNenAbilityEffect(abilityEffect, caster, getNenPower());
                setInitialCooldown();
            }
            else {
                nenCaster.nen$giveNen(this.getTotalCost());
            }
        }
    }
}
