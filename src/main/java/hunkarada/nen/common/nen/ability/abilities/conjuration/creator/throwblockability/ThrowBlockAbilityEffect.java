package hunkarada.nen.common.nen.ability.abilities.conjuration.creator.throwblockability;

import hunkarada.nen.common.nen.IEntityNen;
import hunkarada.nen.common.nen.NenMemory;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class ThrowBlockAbilityEffect extends AbilityEffect {
    ThrowBlockAbilityEffect(){
    }

    @Override
    protected void instantEffect(Entity target) {

    }

    @Override
    protected void firstTickEffect(Entity target) {
        IEntityNen caster = (IEntityNen) getCaster();
        String nenMemory = caster.nen$readFromNenMemory(NenMemory.THROW_BLOCK_ABILITY_EFFECT_STAGE);
        if (nenMemory != null){
            switch (nenMemory){
                case "glass" -> {}
                case "obsidian" -> {}
                case "tnt" -> {}
            }
        }
        else {
            caster.nen$writeToNenMemory(NenMemory.THROW_BLOCK_ABILITY_EFFECT_STAGE, "glass");
        }
    }

    @Override
    protected void instantEffect(BlockPos target) {
    }

    @Override
    protected void durationalEffect(Entity target) {
    }

    @Override
    protected void onRemoveEffect(Entity target) {
    }
}
