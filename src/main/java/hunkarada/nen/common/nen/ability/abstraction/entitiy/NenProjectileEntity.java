package hunkarada.nen.common.nen.ability.abstraction.entitiy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class NenProjectileEntity extends ProjectileEntity {
    public NenProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }


    protected abstract void createProjectile(Vec3d pos, Vec3d direction);
}
