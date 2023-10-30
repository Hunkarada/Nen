package hunkarada.nen.common.nen.abilities.abstractions;

import net.minecraft.client.model.Model;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NenCollisionEntity extends Entity {

    Model model;

    public static final EntityType<NenCollisionEntity> NEN_COLLISION_ENTITY = null; // need to register that

    int lifetime;

    public NenCollisionEntity(EntityType<?> type, World world) {
        super(type, world);
    }


    public void spawn(Vec3d pos){
       world.spawnEntity(new NenCollisionEntity());
    }

}
