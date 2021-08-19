package net.kunmc.lab.futoize;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public class SizeChanger {
    private static final SizeChanger INSTANCE = new SizeChanger();
    private float currentSize = 10;

    public static SizeChanger getInstance() {
        return INSTANCE;
    }

    public float getSize(Entity entity) {
        if (!isEnableChangeSize() || entity instanceof PlayerEntity)
            return 1f;

        if (isRandomSize()) {
            Random random = new Random(entity.getUUID().hashCode());
            return random.nextFloat() * getCurrentSize();
        }

        return getCurrentSize();
    }

    public float getCurrentSize() {
        return currentSize;
    }

    public boolean isEnableChangeSize() {
        return true;
    }

    public boolean isRandomSize() {
        return true;
    }
}
