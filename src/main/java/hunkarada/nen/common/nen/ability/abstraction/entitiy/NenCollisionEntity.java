package hunkarada.nen.common.nen.ability.abstraction.entitiy;

import hunkarada.nen.common.nen.ability.abstraction.shape.Shape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class NenCollisionEntity extends Entity {

    int lifetime;
    Shape shape;
    Vec3d[] positions;

    float radius;

    public static final EntityType<NenCollisionEntity> NEN_COLLISION_ENTITY = null; // need to register that

    public NenCollisionEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    protected void checkForCollision(){
        switch (shape){
            case CUBE -> {} // positions
            case SPHERE -> {} // radius
        }
    }
    @Override
    public void tick(){
        super.tick();
        checkForCollision();
    }
}
