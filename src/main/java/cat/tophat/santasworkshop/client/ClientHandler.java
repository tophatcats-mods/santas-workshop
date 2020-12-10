package cat.tophat.santasworkshop.client;

import cat.tophat.santasworkshop.client.rendering.BallisticPenguinGeoRenderer;
import cat.tophat.santasworkshop.client.rendering.ElfGeoRenderer;
import cat.tophat.santasworkshop.common.init.InitMobs;
import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {

    public static void startup(IEventBus mod, IEventBus forge) {
        mod.addListener(ClientHandler::setup);
    }

    private static void setup(final FMLClientSetupEvent event) {
        Minecraft minecraft = event.getMinecraftSupplier().get();
        RenderingRegistry.registerEntityRenderingHandler(InitMobs.BALLISTIC_PENGUIN, BallisticPenguinGeoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitMobs.ELF_ENTITY, ElfGeoRenderer::new);
    }
}
