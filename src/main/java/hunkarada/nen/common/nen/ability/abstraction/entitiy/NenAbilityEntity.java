package hunkarada.nen.common.nen.ability.abstraction.entitiy;

import hunkarada.nen.common.NenMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public abstract class NenAbilityEntity extends Entity {
    public static final Identifier NEN_ABILITY_ENTITY_ID = Identifier.of(NenMod.MOD_ID, "nen_ability_entity");
    int lifetime;
    PlayerEntity caster;

    public NenAbilityEntity(EntityType<?> type, World world) {
        super(type, world);
    }


    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
