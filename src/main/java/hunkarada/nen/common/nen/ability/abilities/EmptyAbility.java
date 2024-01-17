package hunkarada.nen.common.nen.ability.abilities;

import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;

public class EmptyAbility extends Ability {
    public EmptyAbility(){
        super();
        this.id = "empty_ability";
    }
    @Override
    public void cast(PlayerEntity caster) {

    }
}
