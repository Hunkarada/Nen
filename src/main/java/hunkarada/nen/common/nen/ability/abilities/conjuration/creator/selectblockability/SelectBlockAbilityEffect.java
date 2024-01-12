package hunkarada.nen.common.nen.ability.abilities.conjuration.creator.selectblockability;

import hunkarada.nen.common.nen.IEntityNen;
import hunkarada.nen.common.nen.NenMemory;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class SelectBlockAbilityEffect extends AbilityEffect {


    @Override
    protected void firstTickEffect(Entity target) {

    }

    @Override
    protected void firstTickEffect(BlockPos target) {
        System.out.println("HUI");
        IEntityNen casterNen = (IEntityNen) caster;
        casterNen.nen$writeToNenMemory(NenMemory.SELECTED_BLOCK, target.toShortString());
    }

    @Override
    public void durationalEffect(Entity target) {

    }
}
