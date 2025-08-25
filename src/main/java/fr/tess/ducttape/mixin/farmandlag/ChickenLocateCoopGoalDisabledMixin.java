package fr.tess.ducttape.mixin.farmandlag;

import net.satisfy.farm_and_charm.core.entity.ai.ChickenLocateCoopGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ChickenLocateCoopGoal.class, remap = false)
public class ChickenLocateCoopGoalDisabledMixin {
    @Inject(method = "m_8036_", at = @At("HEAD"), cancellable = true)
    private void disableChickenLocateGoal(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
