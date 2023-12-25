package hunkarada.nen.common.nen.ability.abstraction.ability;

import net.minecraft.entity.player.PlayerEntity;

public abstract class SelfAbility extends Ability {
    @Override
    public void cast(PlayerEntity caster){
        prepareCast(caster);
    }
}
