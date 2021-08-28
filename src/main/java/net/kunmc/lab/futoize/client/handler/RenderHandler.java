package net.kunmc.lab.futoize.client.handler;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.kunmc.lab.futoize.data.SizeChangerManager;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderHandler {

    @SubscribeEvent
    public static void onLivingRenderPre(RenderLivingEvent.Pre e) {
        MatrixStack matrix = e.getMatrixStack();
        matrix.pushPose();
        float sc = SizeChangerManager.getInstance().getSize(e.getEntity());
        matrix.scale(sc, sc, sc);
    }

    @SubscribeEvent
    public static void onLivingRenderPre(RenderLivingEvent.Post e) {
        e.getMatrixStack().popPose();
    }
}
