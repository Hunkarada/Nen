package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenCollisionEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public abstract class AoeAbility extends Ability {
    protected NenCollisionEntity aoeEntity;
    protected abstract void spawnAoeEntity(Vec3d pos);
    protected abstract void spawnAoeEntities();
    protected abstract Vec3d calcPosToSpawnAoeEntity(LivingEntity caster);
    @Override
    public void cast(PlayerEntity caster){
        IPlayerEntityNen nenCaster = (IPlayerEntityNen) caster;
        if (isNotAtCooldown() && nenCaster.nen$getIsNenAwakened() && nenCaster.nen$getIsNenActive()){
            // calculate cost and other things
            prepareCast(caster);
            if (nenCaster.nen$collectNen(this.getTotalCost())){
                spawnAoeEntities();
                setInitialCooldown();
            }
        }
    }
}

