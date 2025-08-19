package fr.tess.ducttape.mixin.VanillaGolf;

import net.golf.golf.enchantment.WedgingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = WedgingEnchantment.class, remap = false)
public class EnchentmentMixin {
     @Inject(method = "m_5975_", at = @At("HEAD"), cancellable = true)
     private void moreKb(Enchantment ench, CallbackInfoReturnable<Boolean> cir) {
         if (ench == (Enchantment)(Object)this || ench == Enchantments.SHARPNESS)
             cir.setReturnValue(false);
         cir.setReturnValue(true);
     }
}
