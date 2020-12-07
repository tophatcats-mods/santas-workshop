package cat.tophat.santasworkshop.common.init;

import cat.tophat.santasworkshop.SantasWorkshop;
import cat.tophat.santasworkshop.common.mobs.BallisticPenguinEntity;
import cat.tophat.santasworkshop.common.mobs.ElfEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = SantasWorkshop.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InitMobs {

    @ObjectHolder(SantasWorkshop.MOD_ID + ":ballistic_penguin")
    public static final EntityType<BallisticPenguinEntity> BALLISTIC_PENGUIN
            = (EntityType<BallisticPenguinEntity>) EntityType.Builder.create(BallisticPenguinEntity::new,
            EntityClassification.MONSTER)
            .size(1.0F, 1.0F)
            .setTrackingRange(80)
            .setUpdateInterval(1)
            .setShouldReceiveVelocityUpdates(true)
            .build(SantasWorkshop.MOD_ID + ":ballistic_penguin")
            .setRegistryName(SantasWorkshop.MOD_ID, "ballistic_penguin");

    @ObjectHolder(SantasWorkshop.MOD_ID + ":elf")
    public static final EntityType<ElfEntity> ELF_ENTITY
            = (EntityType<ElfEntity>) EntityType.Builder.create(ElfEntity::new,
            EntityClassification.MONSTER)
            .size(1.0F, 1.0F)
            .setTrackingRange(80)
            .setUpdateInterval(1)
            .setShouldReceiveVelocityUpdates(true)
            .build(SantasWorkshop.MOD_ID + ":elf")
            .setRegistryName(SantasWorkshop.MOD_ID, "elf");

    @SubscribeEvent
    public static void registerEntity(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
                BALLISTIC_PENGUIN,
                ELF_ENTITY
        );
    }
}
