package hunkarada.nen.common.nen.ability.abilities.conjuration.creator.throwblockability;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ThrowBlockAbilityEntity extends NenProjectileEntity {
    public static final Identifier THROW_BLOCK_ABILITY_ENTITY_ID = Identifier.of(NenMod.MOD_ID,"throw_block_ability");
    public ThrowBlockAbilityEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
        setAbility(new ThrowBlockAbility());
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public void tick(){
       this.setVelocity(0, 0.01f, 0);
       this.setPos(this.getPos().x + this.getVelocity().x,this.getPos().y + this.getVelocity().y, this.getPos().z + this.getVelocity().z);
       super.tick();
    }

}
