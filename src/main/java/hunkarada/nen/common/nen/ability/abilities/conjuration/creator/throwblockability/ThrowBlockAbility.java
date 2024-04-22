package hunkarada.nen.common.nen.ability.abilities.conjuration.creator.throwblockability;

import hunkarada.nen.common.nen.ability.abstraction.ability.ProjectileAbility;
import hunkarada.nen.common.register.NenModRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class ThrowBlockAbility extends ProjectileAbility {
    public ThrowBlockAbility(){
        super();
    }

    @Override
    public void setupAbility() {
        this.abilityEffect = new ThrowBlockAbilityEffect();
        this.initialCooldown = 20;
        this.staticCost = 0;
        this.dynamicCostPercent = 0;
        this.id = "throw_block_ability";
    }

    @Override
    protected ArrayList<Vec3d> calcPosAndDirection(PlayerEntity caster) {
        ArrayList<Vec3d> pos = new ArrayList<>();
        pos.add(caster.getPos().add(0, 1, 0));
        pos.add(caster.getRotationVec(0));
        return pos;
    }

    @Override
    protected void spawnProjectileEntities() {
        spawnProjectileEntity(calcPosAndDirection(this.getCaster()));
    }

    @Override
    protected void spawnProjectileEntity(ArrayList<Vec3d> posAndDirection) {
        ThrowBlockAbilityEntity abilityEntity = new ThrowBlockAbilityEntity(NenModRegister.THROW_BLOCK_ABILITY_ENTITY, getCaster().getWorld());
        abilityEntity.setPosition(posAndDirection.remove(0));
//        abilityEntity.setVelocity(posAndDirection.remove(0));

        getCaster().getWorld().spawnEntity(abilityEntity);
    }
}
