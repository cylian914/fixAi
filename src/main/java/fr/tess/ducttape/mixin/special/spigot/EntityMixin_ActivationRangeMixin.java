package fr.tess.ducttape.mixin.special.spigot;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(value = org.spigotmc.ActivationRange.class, priority = 1001, remap = false)
public abstract class EntityMixin_ActivationRangeMixin {
    @Inject(remap = false, method = "checkIfActive", at = @At(value = "HEAD"), cancellable = true)
    private static void fakeTicked(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}