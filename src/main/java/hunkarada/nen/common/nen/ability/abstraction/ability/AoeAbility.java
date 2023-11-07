package hunkarada.nen.common.nen.ability.abstraction.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public abstract class AoeAbility extends Ability {

    protected abstract Vec3d calcPos(LivingEntity caster);

}
