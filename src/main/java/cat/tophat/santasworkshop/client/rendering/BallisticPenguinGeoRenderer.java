package cat.tophat.santasworkshop.client.rendering;

import cat.tophat.santasworkshop.client.models.BallisticPenguinModel;
import cat.tophat.santasworkshop.common.mobs.BallisticPenguinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BallisticPenguinGeoRenderer extends GeoEntityRenderer<BallisticPenguinEntity> {

    public BallisticPenguinGeoRenderer(EntityRendererManager renderManager) {
        super(renderManager, new BallisticPenguinModel());
    }
}
