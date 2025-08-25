package fr.tess.ducttape.mixin.farmandlag;

import net.satisfy.farm_and_charm.core.network.PacketHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PacketHandler.class, remap = false)
public class PacketHandlerMixin {
    @Inject(method = {"sendToClient", "sendToServer", "sendSaturationSync"}, at = @At("HEAD"), cancellable = true)
    private static void cancelPacketSend(CallbackInfo ci) {
        ci.cancel();
    }
}
