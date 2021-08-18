package net.kunmc.lab.futoize.mixin;

import net.kunmc.lab.futoize.SizeChanger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void getDimensions(Pose p_213305_1_, CallbackInfoReturnable<EntitySize> cir) {
        EntitySize size = cir.getReturnValue().scale(SizeChanger.getInstance().getSize((Entity) (Object) this));
        cir.setReturnValue(size);
    }


}
