package fr.tess.ducttape.mixin.VanillaGolf;

import fr.tess.ducttape.BallExtension;
import net.golf.golf.entity.GolfBallEntity;
import net.golf.golf.init.GolfModEntities;
import net.golf.golf.init.GolfModItems;
import net.golf.golf.item.GolfBallItemItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import net.golf.golf.block.GolfCupBlock;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.UUID;

@Mixin(value = GolfCupBlock.class, remap = false)
public class EntityCollisionMixin {

    @Redirect(method = "m_7892_", at = @At(value = "INVOKE", target = "Lnet/golf/golf/procedures/GolfCupEntityCollidesInTheBlockProcedure;execute(Lnet/minecraft/world/level/LevelAccessor;DDDLnet/minecraft/world/entity/Entity;)V"))
    private void hitGoal(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (!(entity instanceof GolfBallEntity ball))
            return;
        int score = ((BallExtension)ball).getScore();
        if (score == 0)
            return;
        UUID pUUID = ((BallExtension)ball).getPlayer();
        if (pUUID == null)
            return;
        Player playerScored = ball.level().getPlayerByUUID(pUUID);
        if (playerScored == null)
            return;
        AABB SearchSize = AABB.ofSize(new Vec3(x, y, z), 50.0, 50.0, 50.0); //TODO dynamic or integration with other mods (like the speaker one)
        List<Player> brodcast = playerScored.level().getEntitiesOfClass(Player.class, SearchSize);
        brodcast.remove(playerScored);

        Component text = playerScored.getName().copy().append("§e§o finished golfing with a score of " + score);
        brodcast.forEach((e) -> {
            e.sendSystemMessage(text);
        });
        playerScored.sendSystemMessage(text);
        ((BallExtension)ball).setScore(0);
    }
}