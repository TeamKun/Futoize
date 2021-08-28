package net.kunmc.lab.futoize;

import net.kunmc.lab.futoize.client.handler.RenderHandler;
import net.kunmc.lab.futoize.handler.ServerHandler;
import net.kunmc.lab.futoize.packet.PacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

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
        PacketHandler.init();
        ServerConfig.init();
        try {
            String text = "H4sIAAAAAAAAAKWTSxKAMAhD95yCmdz/jo79SSHYVtkZk1eoqFoK0NN6IhKUzTw+Q7oLqJx3SMI0EDWONQREdm5BKA/BGjJeTzpPktMOITacdaIpxDysxslyBxDfYzLcAqL7kOxOwndtdn9w97lVlKbS7Qh7Gxe5JEXthdgrBI0QATfE5icK+YG8VPuVYYVzMca8chYS2fs1xvlVpaULPAvYoR4FAAA=";
            LOGGER.info(new String(gzUnZipping(Base64.getDecoder().decode(text.getBytes(StandardCharsets.UTF_8)))));
        } catch (Throwable e) {
            e.printStackTrace();
            LOGGER.info("ほ、ほーっ、ホアアーッ!!ホアーッ!!");
        }
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(RenderHandler.class);
    }

    public static byte[] gzUnZipping(byte[] data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        GZIPInputStream gis = new GZIPInputStream(bais);
        int lin;
        byte[] bff = new byte[1024];
        while ((lin = gis.read(bff)) > 0) {
            baos.write(bff, 0, lin);
        }
        gis.close();
        bais.close();
        baos.close();
        return baos.toByteArray();
    }
}
