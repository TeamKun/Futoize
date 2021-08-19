package net.kunmc.lab.futoize;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerConfig {
    private static final Logger LOGGER = LogManager.getLogger(ServerConfig.class);
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE;
    public static ForgeConfigSpec.ConfigValue<Boolean> RANDOM;
    public static ForgeConfigSpec.ConfigValue<Float> SIZE;

    public static void init() {
        Pair<ConfigLoder, ForgeConfigSpec> server_config = new ForgeConfigSpec.Builder().configure(ConfigLoder::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, server_config.getRight());
    }

    public static class ConfigLoder {
        public ConfigLoder(ForgeConfigSpec.Builder builder) {
            LOGGER.info("Loading Server Config");
            builder.push("Futoize");
            ENABLE = builder.define("Enable", false);
            RANDOM = builder.define("Random", false);
            SIZE = builder.define("Size", 1f);
            builder.pop();
        }
    }
}
