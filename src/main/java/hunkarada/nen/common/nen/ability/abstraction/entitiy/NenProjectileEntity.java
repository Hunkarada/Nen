package hunkarada.nen.common.nen.ability.abstraction.entitiy;

import hunkarada.nen.common.nen.IEntityNen;
import hunkarada.nen.common.nen.ability.abstraction.ability.Ability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class NenProjectileEntity extends ProjectileEntity {

    private Ability ability;
    public NenProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);

    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        IEntityNen entityNen = (IEntityNen) entity;
        entityNen.nen$applyNenAbilityEffect(ability.getAbilityEffect(), (PlayerEntity) this.getOwner(), ability.getNenPower());

    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        BlockPos blockPos = blockHitResult.getBlockPos();
        ability.getAbilityEffect().applyEffectOnBlock(blockPos, (PlayerEntity) this.getOwner(), ability.getNenPower());
    }
    protected void setAbility(Ability ability){
        this.ability = ability;
    }

}
