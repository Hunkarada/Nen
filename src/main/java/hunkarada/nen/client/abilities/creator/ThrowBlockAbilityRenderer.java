package hunkarada.nen.client.abilities.creator;

import hunkarada.nen.common.nen.ability.abilities.conjuration.creator.throwblockability.ThrowBlockAbilityEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class ThrowBlockAbilityRenderer extends EntityRenderer<ThrowBlockAbilityEntity> {
    public ThrowBlockAbilityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(ThrowBlockAbilityEntity entity) {
        return null;
    }

}
