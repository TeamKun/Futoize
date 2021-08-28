package net.kunmc.lab.futoize.client.handler;

import net.kunmc.lab.futoize.data.SizeChangerManager;
import net.kunmc.lab.futoize.packet.SizeSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SizeSyncMessageHandler {
    public static void reversiveMessage(SizeSyncMessage message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(true);
        SizeChangerManager manager = SizeChangerManager.getInstance();
        manager.setCurrentSize(message.size);
        manager.setEnable(message.enable);
        manager.setRandom(message.random);
        for (Entity entity : Minecraft.getInstance().level.entitiesForRendering()) {
            entity.refreshDimensions();
        }
    }
}
