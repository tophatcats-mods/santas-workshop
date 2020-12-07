package cat.tophat.santasworkshop.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {

    public static void startup(IEventBus mod, IEventBus forge) {
    }

    private static void setup(final FMLClientSetupEvent event) {
        Minecraft minecraft = event.getMinecraftSupplier().get();
    }
}
