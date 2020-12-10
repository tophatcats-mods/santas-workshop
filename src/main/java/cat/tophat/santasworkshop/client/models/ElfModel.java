package cat.tophat.santasworkshop.client.models;

import cat.tophat.santasworkshop.SantasWorkshop;
import cat.tophat.santasworkshop.common.mobs.ElfEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ElfModel extends AnimatedGeoModel<ElfEntity> {

    @Override
    public ResourceLocation getModelLocation(ElfEntity entity) {
        return new ResourceLocation(SantasWorkshop.MOD_ID, "geo/mobs/elf.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ElfEntity entity) {
        return new ResourceLocation(SantasWorkshop.MOD_ID, "textures/mobs/elf.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ElfEntity elfEntity) {
        return new ResourceLocation(SantasWorkshop.MOD_ID, "animations/mobs/elf.animation.json");
    }
}
