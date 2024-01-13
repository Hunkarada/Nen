package hunkarada.nen.common.nen.ability.abilities.test;


import hunkarada.nen.common.NenMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.world.World;


public class TestAbilityEntity extends ThrownItemEntity {

    //public static final Identifier TEST_ABILITY_ID = new Identifier(NenMod.MOD_ID, "test_ability_entity");
    public TestAbilityEntity(EntityType<? extends ThrownItemEntity> type, World world) {
        super(type, world);
    }

    public TestAbilityEntity(LivingEntity livingEntity, World world)  {
        super(TestEntitie.TEST ,livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket () {
        return  new EntitySpawnS2CPacket(this);
    }
}
