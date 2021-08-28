package net.kunmc.lab.futoize.handler;

import net.kunmc.lab.futoize.command.FCommands;
import net.kunmc.lab.futoize.packet.PacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerHandler {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
        if (!e.getPlayer().level.isClientSide())
            PacketHandler.sendSyncMessage((ServerPlayerEntity) e.getPlayer());
    }

    @SubscribeEvent
    public static void onPlayerJoin(LivingSpawnEvent e) {
        if (e.getEntity() instanceof ServerPlayerEntity && !e.getEntity().level.isClientSide())
            PacketHandler.sendSyncMessage((ServerPlayerEntity) e.getEntity());
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent e) {
        FCommands.registerCommand(e.getDispatcher());
    }
}
