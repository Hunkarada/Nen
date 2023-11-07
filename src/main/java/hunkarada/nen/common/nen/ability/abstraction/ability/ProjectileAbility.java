package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenProjectileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public abstract class ProjectileAbility extends Ability {
    NenProjectileEntity projectile;


    protected abstract ArrayList<Vec3d> calcPosAndDirection(LivingEntity caster);
}
