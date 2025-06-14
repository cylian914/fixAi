package fr.cyn.fixai.mixin;

import io.izzel.arclight.common.mixin.optimization.general.activationrange.EntityMixin_ActivationRange;
import io.izzel.arclight.common.mod.ArclightConstants;
import net.minecraft.world.entity.Entity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Entity.class, priority = 1001)
public abstract class EntityMixin_ActivationRangeMixin {

    @Redirect(remap = false, method = "bridge$updateActivation", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/Entity;activatedTick:J", opcode = Opcodes.GETFIELD, remap = false))
    public long fakeTicked(Entity e) {
        return 0;
    }

    @Redirect(remap = false, method = "bridge$updateActivation", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/Entity;defaultActivationState:Z", opcode = Opcodes.GETFIELD, remap = false))
    public boolean fakeTicked2(Entity e) {
        return true;
    }
}