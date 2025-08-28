package fr.tess.ducttape.mixin.mts;

import dev.ftb.mods.ftbchunks.api.FTBChunksAPI;
import dev.ftb.mods.ftblibrary.math.ChunkDimPos;
import fr.tess.ducttape.DuctTape;
import mcinterface1201.WrapperWorld;
import minecrafttransportsimulator.baseclasses.Point3D;
import minecrafttransportsimulator.blocks.components.ABlockBase;
import minecrafttransportsimulator.mcinterface.IWrapperPlayer;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = WrapperWorld.class, remap = false)
public class WrapperWorldMixin {
    @Shadow @Final protected Level world;

    @Inject(method = "destroyBlock", at = @At("HEAD"), cancellable = true)
    private void ftbChunkCompat(Point3D position, boolean spawnDrops, CallbackInfo ci) {
        if (FTBChunksAPI.api().getManager().getChunk(new ChunkDimPos(world.dimension(), ((int) position.x) >> 4, ((int) position.z) >> 4)) != null) {
            DuctTape.LOGGER.warn("stoped");
            ci.cancel();
            return;
        }
        DuctTape.LOGGER.warn("continue");
    }
}
