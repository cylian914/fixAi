package fr.tess.ducttape.mixin.VanillaGolf;

import fr.tess.ducttape.DuctTape;
import net.golf.golf.init.GolfModEnchantments;
import net.golf.golf.init.GolfModItems;
import net.golf.golf.procedures.HitwithClubProcedure;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HitwithClubProcedure.class, remap = false)
public class hurtEnemyMixin {
    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private static void execute(Entity entity, Entity sourceentity, CallbackInfo ci) {
        if (!(sourceentity instanceof LivingEntity))
            ci.cancel();
        LivingEntity le = (LivingEntity) sourceentity;
        float enchantBonus = 0.5f * EnchantmentHelper.getEnchantments(le.getMainHandItem()).get(GolfModEnchantments.WEDGING.get());
        int weaponBonus = getBonus(le.getMainHandItem());

        float finalBonus = enchantBonus + weaponBonus;
        entity.addDeltaMovement(new Vec3(sourceentity.getLookAngle().x * finalBonus, Math.abs(sourceentity.getLookAngle().y), sourceentity.getLookAngle().z * finalBonus));
        ci.cancel();
    }

    //TODO make it expendable
    @Unique
    private static int getBonus(ItemStack item) {
        if (item.is(GolfModItems.WOODEN_CLUB.get()))
            return 1;
        if (item.is(GolfModItems.STONE_CLUB.get()))
            return 2;
        if (item.is(GolfModItems.DIAMOND_CLUB.get()))
            return 3;
        if (item.is(GolfModItems.NETHERITE_CLUB.get()))
            return 4;
        if (item.is(GolfModItems.GOLDEN_CLUB.get()))
            return 5;
        return 1;
    }
}
