package net.kunmc.lab.futoize.handler;

import net.kunmc.lab.futoize.SizeChanger;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerHandler {
    @SubscribeEvent
    public static void onSize(EntityEvent.Size e) {
        float sc = SizeChanger.getInstance().getSize(e.getEntity());
        //  e.setNewEyeHeight(e.getOldEyeHeight() * sc);

        // e.setNewSize(new EntitySize(e.getOldSize().width * sc, e.getOldSize().height * sc * 0.75f, e.getOldSize().fixed), true);
    }
}
