package hunkarada.nen.common.nen.ability.abilities.conjuration.creator.throwblockability;

import hunkarada.nen.common.nen.IEntityNen;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import hunkarada.nen.common.nen.ability.abstraction.ability.NenMemory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.math.BlockPos;

public class ThrowBlockAbilityEffect extends AbilityEffect {
    ThrowBlockAbilityEffect(){
    }
    @Override
    protected void instantEffect(Entity target) {
        IEntityNen caster = (IEntityNen) getCaster();
        String nenMemoryEntry = caster.nen$readFromNenMemory(NenMemory.THROW_BLOCK_ABILITY_EFFECT_STAGE);
        if (nenMemoryEntry != null){
            switch (nenMemoryEntry){
                case "glass" -> {
                    target.damage(new DamageSources(DynamicRegistryManager.EMPTY).generic(), 6f);
                }
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
