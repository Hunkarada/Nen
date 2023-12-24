package hunkarada.nen.client.render.ability;

import hunkarada.nen.common.nen.ability.abstraction.entitiy.NenAbilityEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class SelectBlockAbilityRenderer extends EntityRenderer<NenAbilityEntity> {
    protected SelectBlockAbilityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(NenAbilityEntity entity) {
        return null;
    }
}
