package net.kunmc.lab.futoize.packet;

import net.kunmc.lab.futoize.Futoize;
import net.kunmc.lab.futoize.client.handler.SizeSyncMessageHandler;
import net.kunmc.lab.futoize.data.SizeChangerManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Futoize.MODID, Futoize.MODID + "_channel")).clientAcceptedVersions(a -> true).serverAcceptedVersions(a -> true).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();
    private static int next = 0;

    public static void init() {
        INSTANCE.registerMessage(next++, SizeSyncMessage.class, SizeSyncMessage::encodeMessege, SizeSyncMessage::decodeMessege, SizeSyncMessageHandler::reversiveMessage);
    }

    public static void sendSyncMessage() {
        SizeChangerManager manager = SizeChangerManager.getInstance();
        INSTANCE.send(PacketDistributor.ALL.noArg(), new SizeSyncMessage(manager.getCurrentSize(), manager.isEnable(), manager.isRandom()));
    }

    public static void sendSyncMessage(ServerPlayerEntity playerEntity) {
        SizeChangerManager manager = SizeChangerManager.getInstance();
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> playerEntity), new SizeSyncMessage(manager.getCurrentSize(), manager.isEnable(), manager.isRandom()));
    }
}
