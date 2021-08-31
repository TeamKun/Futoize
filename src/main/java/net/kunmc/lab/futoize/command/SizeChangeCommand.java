package net.kunmc.lab.futoize.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.kunmc.lab.futoize.Futoize;
import net.kunmc.lab.futoize.data.SizeChangerManager;
import net.kunmc.lab.futoize.packet.PacketHandler;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class SizeChangeCommand {
    public static void register(CommandDispatcher<CommandSource> d) {
        d.register(Commands.literal(Futoize.MODID).requires((source) -> source.hasPermission(2))
                .then(Commands.literal("enable").executes((context -> setEnable(context.getSource(), true))))
                .then(Commands.literal("disable").executes((context -> setEnable(context.getSource(), false))))
                .then(Commands.literal("size").then(Commands.argument("value", FloatArgumentType.floatArg(0)).executes((context -> setSize(context.getSource(), FloatArgumentType.getFloat(context, "value"))))))
                .then(Commands.literal("random").then(Commands.argument("enable", BoolArgumentType.bool()).executes((context -> setRandom(context.getSource(), BoolArgumentType.getBool(context, "enable"))))))
        );
    }

    private static int setSize(CommandSource src, float size) {
        src.sendSuccess(new StringTextComponent("巨大化倍率を").append(size + "倍にしました"), true);
        SizeChangerManager manager = SizeChangerManager.getInstance();
        manager.setCurrentSize(size);
        PacketHandler.sendSyncMessage();
        return 1;
    }

    private static int setEnable(CommandSource src, boolean enable) {
        SizeChangerManager manager = SizeChangerManager.getInstance();
        if (enable && manager.isEnable()) {
            src.sendFailure(new StringTextComponent("すでに巨大化は有効化されています"));
            return 1;
        } else if (!enable && !manager.isEnable()) {
            src.sendFailure(new StringTextComponent("すでに巨大化は無効化されています"));
            return 1;
        }

        if (enable)
            src.sendSuccess(new StringTextComponent("巨大化を有効化しました"), true);
        else
            src.sendSuccess(new StringTextComponent("巨大化を無効化しました"), true);

        manager.setEnable(enable);
        for (ServerWorld level : src.getServer().getAllLevels()) {
            level.getEntities().forEach(Entity::refreshDimensions);
        }
        PacketHandler.sendSyncMessage();
        return 1;
    }

    private static int setRandom(CommandSource src, boolean random) {
        SizeChangerManager manager = SizeChangerManager.getInstance();

        if (random && manager.isRandom()) {
            src.sendFailure(new StringTextComponent("すでにランダムは有効化されています"));
            return 1;
        } else if (!random && !manager.isRandom()) {
            src.sendFailure(new StringTextComponent("すでにランダムは無効化されています"));
            return 1;
        }

        if (random)
            src.sendSuccess(new StringTextComponent("ランダムを有効化しました"), true);
        else
            src.sendSuccess(new StringTextComponent("ランダムを無効化しました"), true);


        manager.setRandom(random);
        for (ServerWorld level : src.getServer().getAllLevels()) {
            level.getEntities().forEach(Entity::refreshDimensions);
        }
        PacketHandler.sendSyncMessage();
        return 1;
    }
}
