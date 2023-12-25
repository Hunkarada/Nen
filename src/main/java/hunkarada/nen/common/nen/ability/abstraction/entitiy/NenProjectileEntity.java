package hunkarada.nen.common.nen.ability.abstraction.entitiy;

import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NenProjectileEntity extends ProjectileEntity {
    AbilityEffect abilityEffect;
    public NenProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world, AbilityEffect abilityEffect) {
        super(entityType, world);
        this.abilityEffect = abilityEffect;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        abilityEffect.applyEffect(entity, (PlayerEntity) this.getOwner());

    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        BlockPos blockPos = blockHitResult.getBlockPos();
        abilityEffect.applyEffect(blockPos, (PlayerEntity) this.getOwner());
    }

    @Override
    protected void initDataTracker() {

    }
}
