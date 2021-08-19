package net.kunmc.lab.futoize;

import net.kunmc.lab.futoize.client.handler.RenderHandler;
import net.kunmc.lab.futoize.handler.ServerHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Futoize.MODID)
public class Futoize {
    public static final String MODID = "futoize";
    private static final Logger LOGGER = LogManager.getLogger();

    public Futoize() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(ServerHandler.class);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ServerConfig.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(RenderHandler.class);
    }
}
