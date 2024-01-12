package hunkarada.nen.common.nen.ability.abstraction.entitiy;

import hunkarada.nen.common.NenMod;
import hunkarada.nen.common.nen.IEntityNen;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class NenCollisionEntity extends Entity {
    public static final Identifier NEN_COLLISION_ENTITY_ID = new Identifier(NenMod.MOD_ID, "nen_collision_entity");
    PlayerEntity caster;
    Box collisionBox;
    AbilityEffect abilityEffect;
    int lifetime;
    double radius;
    boolean isSphere;
    boolean isMass;

    public NenCollisionEntity(EntityType<?> type, World world) {
        super(type, world);
        calcRadius();
    }

    public void calcRadius(){
        radius = ((Math.max(collisionBox.getXLength(), collisionBox.getZLength())) / 2);
    }

    // Here we check for any collision with entities.
    // If we found more than one entity,
    // we cast ability on the closest entity or on all of them (depends on @param isMass)
    protected void checkForCollision(){
        List<Entity> entityList = this.world.getOtherEntities(this, collisionBox);
        if (!entityList.isEmpty()) {
            Vec3d center = collisionBox.getCenter();
            List<Entity> filteredList = new ArrayList<>();
            if (isSphere) {
                for (Entity entity : entityList) {
                    if (Math.sqrt(entity.getBlockPos().getSquaredDistance(center)) <= radius) {
                        filteredList.add(entity);
                    }
                }
            } else {
                filteredList.addAll(entityList);
            }
            if (isMass) {
                for (Entity entity : filteredList) {
                    IEntityNen entityNen = (IEntityNen) entity;
                    entityNen.nen$addNenAbilityEffect(abilityEffect, caster, abilityEffect.getNenPower());
                }
            } else {
                Entity closestEntity = filteredList.get(0);
                for (Entity entity : filteredList) {
                    if (entity.getBlockPos().getSquaredDistance(center) < closestEntity.getBlockPos().getSquaredDistance(center)) {
                        closestEntity = entity;
                    }
                }
                IEntityNen entityNen = (IEntityNen) closestEntity;
                entityNen.nen$addNenAbilityEffect(abilityEffect, caster, abilityEffect.getNenPower());
            }
        }


    }
    private void checkLifetime(){
        if (lifetime == 0){
            this.kill();
        }
        else {
            lifetime -= 1;
        }
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public void tick(){
        super.tick();
        checkForCollision();
        checkLifetime();
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
