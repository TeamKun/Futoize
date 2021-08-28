package net.kunmc.lab.futoize.data;

import net.kunmc.lab.futoize.util.ServerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SizeChangerManager {
    private static final SizeChangerManager INSTANCE = new SizeChangerManager();
    private float currentSize = 1;
    private boolean enable;
    private boolean random;

    public static SizeChangerManager getInstance() {
        return INSTANCE;
    }

    public float getSize(Entity entity) {
        if (!isEnable() || entity instanceof PlayerEntity)
            return 1f;

        if (isRandom()) {
            Random random = new Random(entity.getUUID().hashCode());
            return random.nextFloat() * getCurrentSize();
        }

        return getCurrentSize();
    }

    public float getCurrentSize() {
        return currentSize;
    }

    public boolean isEnable() {
        return enable;
    }

    public boolean isRandom() {
        return random;
    }

    public void setCurrentSize(float currentSize) {
        boolean upFlg = this.currentSize != currentSize;
        this.currentSize = currentSize;
        if (upFlg)
            update();
    }

    public void setEnable(boolean enable) {
        boolean upFlg = this.enable != enable;
        this.enable = enable;
        if (upFlg)
            update();
    }

    public void setRandom(boolean random) {
        boolean upFlg = this.random != random;
        this.random = random;
        if (upFlg)
            update();
    }

    public void update() {
        for (ServerWorld level : ServerUtils.getMinecraftServer().getAllLevels()) {
            level.getEntities().forEach(Entity::refreshDimensions);
        }
    }
}
