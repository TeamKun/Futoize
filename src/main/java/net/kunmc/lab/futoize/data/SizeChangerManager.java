package net.kunmc.lab.futoize.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

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
        this.currentSize = currentSize;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }


}
