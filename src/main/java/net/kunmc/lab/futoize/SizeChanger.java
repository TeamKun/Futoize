package net.kunmc.lab.futoize;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class SizeChanger {
    public static final SizeChanger INSTANCE = new SizeChanger();

    public float getSize(Entity entity) {
        if(entity instanceof PlayerEntity)
            return 1f;
        return 1.5f;
    }

}
