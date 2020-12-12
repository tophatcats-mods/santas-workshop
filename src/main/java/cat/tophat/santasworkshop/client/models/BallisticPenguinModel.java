package cat.tophat.santasworkshop.client.models;

import cat.tophat.santasworkshop.SantasWorkshop;
import cat.tophat.santasworkshop.common.mobs.BallisticPenguinEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BallisticPenguinModel extends AnimatedGeoModel<BallisticPenguinEntity> {

    @Override
    public ResourceLocation getModelLocation(BallisticPenguinEntity entity) {
        return new ResourceLocation(SantasWorkshop.MOD_ID, "geo/mobs/ballisticpenguin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BallisticPenguinEntity entity) {
        return new ResourceLocation(SantasWorkshop.MOD_ID, "textures/mobs/ballisticpenguinnormal.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BallisticPenguinEntity entity) {
        return new ResourceLocation(SantasWorkshop.MOD_ID, "animations/mobs/ballisticpenguin.animation.json");
    }
}
