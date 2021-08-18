package net.kunmc.lab.futoize.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow
    private EntitySize dimensions;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(EntityType<?> p_i48580_1_, World p_i48580_2_, CallbackInfo ci) {
        //this.dimensions = new EntitySize(10, 10, dimensions.fixed);
    }
}
