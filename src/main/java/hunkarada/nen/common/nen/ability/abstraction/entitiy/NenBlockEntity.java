package hunkarada.nen.common.nen.ability.abstraction.entitiy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NenBlockEntity extends Entity {

    public void createFromBlock(BlockPos pos){
        this.setPosition(pos.toCenterPos());
        this.world.removeBlock(pos,false);
        this.world.spawnEntity(this);
    }
    public NenBlockEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

}
