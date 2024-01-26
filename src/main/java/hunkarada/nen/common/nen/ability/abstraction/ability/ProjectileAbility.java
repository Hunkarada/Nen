package hunkarada.nen.common.nen.ability.abstraction.ability;

import hunkarada.nen.common.nen.IPlayerEntityNen;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenProjectileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public abstract class ProjectileAbility extends Ability {
    protected NenProjectileEntity projectile;
    protected abstract ArrayList<Vec3d> calcPosAndDirection(LivingEntity caster);
    protected abstract void spawnProjectileEntities();
    protected abstract void spawnProjectileEntity(ArrayList<Vec3d> posAndDirection);
    @Override
    public void cast(PlayerEntity caster){
        IPlayerEntityNen nenCaster = (IPlayerEntityNen) caster;
        if (isNotAtCooldown() && nenCaster.nen$getIsNenAwakened()){
            // calculate cost and other things
            prepareCast(caster);
            if (nenCaster.nen$collectNen(this.getTotalCost())){
                spawnProjectileEntities();
                setInitialCooldown();
            }
            else {
                nenCaster.nen$giveNen(this.getTotalCost());
            }
        }
    }
}
