package net.kunmc.lab.futoize.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;

public class FCommands {
    public static void registerCommand(CommandDispatcher<CommandSource> d) {
        SizeChangeCommand.register(d);
    }
}
