package cat.tophat.santasworkshop;

import cat.tophat.santasworkshop.client.ClientHandler;
import cat.tophat.santasworkshop.common.init.InitMobs;
import cat.tophat.santasworkshop.common.mobs.BallisticPenguinEntity;
import cat.tophat.santasworkshop.common.mobs.ElfEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(SantasWorkshop.MOD_ID)
public class SantasWorkshop {

    public static final String MOD_ID = "santasworkshop";
    public static final Logger LOGGER = LogManager.getLogger("Santa's Workshop");

    public SantasWorkshop() {
        GeckoLib.initialize();
        IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus(),
                forge = MinecraftForge.EVENT_BUS;

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHandler.startup(mod, forge));
        mod.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            GlobalEntityTypeAttributes.put(InitMobs.BALLISTIC_PENGUIN,
                    BallisticPenguinEntity.createBallisticPenguinAttributes().create());
            GlobalEntityTypeAttributes.put(InitMobs.ELF_ENTITY,
                    ElfEntity.createElfAttributes().create());
            //TODO Add in Nicholas the Regegade when ready.
        });
    }
}
