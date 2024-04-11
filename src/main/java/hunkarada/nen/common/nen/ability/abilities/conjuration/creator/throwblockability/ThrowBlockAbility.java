package hunkarada.nen.common.nen.ability.abilities.conjuration.creator.throwblockability;

import hunkarada.nen.common.nen.ability.abstraction.ability.ProjectileAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class ThrowBlockAbility extends ProjectileAbility {
    ThrowBlockAbility(){
        abilityEffect = new ThrowBlockAbilityEffect();
        initialCooldown = 20;
        staticCost = 0;
        dynamicCostPercent = 0;
    }
    @Override
    protected ArrayList<Vec3d> calcPosAndDirection(LivingEntity caster) {
        return null;
    }

    @Override
    protected void spawnProjectileEntities() {

    }

    @Override
    protected void spawnProjectileEntity(ArrayList<Vec3d> posAndDirection) {

    }
}
