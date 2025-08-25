package fr.tess.ducttape.mixin.farmandlag;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfy.farm_and_charm.core.block.ChickenCoopBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ChickenCoopBlock.class, remap = false)
public class ChickenCoopMixin {
    @Unique
    private static final VoxelShape shape = Shapes.or(
            Block.box(1.0, 0.0, 1.0, 15.0, 12.0, 15.0),
            Block.box(0.0, 12.0, 0.0, 16.0, 14.0, 16.0));

    @Inject(method = "m_5940_", at = @At("HEAD"), cancellable = true)
    public void m_5940_(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        cir.setReturnValue(shape);
    }
}
