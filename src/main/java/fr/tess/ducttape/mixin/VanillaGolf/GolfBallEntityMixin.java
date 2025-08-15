package fr.tess.ducttape.mixin.VanillaGolf;

import fr.tess.ducttape.BallExtension;
import fr.tess.ducttape.DuctTape;
import net.golf.golf.entity.GolfBallEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(value = GolfBallEntity.class, remap = false)
public class GolfBallEntityMixin implements BallExtension {

    @Unique private final TagKey<Item> ducttape$golfClubTag = ItemTags.create(ResourceLocation.parse("golf:golf_club"));

    @Unique private UUID scorePlayer;
    @Unique private int score = 0;

    public UUID getPlayer() {
        return scorePlayer;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Redirect(method = "m_6469_", at = @At(value = "INVOKE", target = "Lnet/golf/golf/procedures/GolfBallEntityIsHurtProcedure;execute(Lnet/minecraft/world/entity/Entity;)V"))
    private void setScore(Entity source) {
        //do nothing
    }

    @Inject(method = "m_6469_", at = @At(value = "HEAD"), cancellable = true)
    private void setScore(DamageSource damagesource, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity source = damagesource.getEntity();
        if (source == null || !(source instanceof Player player))
            return;
        if (!(player.getItemInHand(InteractionHand.MAIN_HAND).is(ducttape$golfClubTag)))
            return;
        score += 1;
        scorePlayer = player.getUUID();
        cir.setReturnValue(true);
    }

    @Inject(method = "m_8099_", at = @At("HEAD"), cancellable = true)
    private void removeMethod(CallbackInfo ci) {
        ci.cancel();
    }

    public void m_21153_(float pHealth) {
       return;
    }
}
