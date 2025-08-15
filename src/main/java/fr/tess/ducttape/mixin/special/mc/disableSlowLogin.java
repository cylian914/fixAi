package fr.tess.ducttape.mixin.special.mc;

import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerLoginPacketListenerImpl.class)
public class disableSlowLogin {
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 600))
    private int loginTimeMultiplayer(int constant) {
        return constant*4;
    }
}
