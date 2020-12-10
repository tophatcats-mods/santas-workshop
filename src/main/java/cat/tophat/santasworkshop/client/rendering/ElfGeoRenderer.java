package cat.tophat.santasworkshop.client.rendering;

import cat.tophat.santasworkshop.client.models.ElfModel;
import cat.tophat.santasworkshop.common.mobs.ElfEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ElfGeoRenderer extends GeoEntityRenderer<ElfEntity> {

    public ElfGeoRenderer(EntityRendererManager renderManager) {
        super(renderManager, new ElfModel());
    }
}
