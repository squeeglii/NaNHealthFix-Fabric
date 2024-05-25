package me.squeeglii.mod.nanfix.mixin;

import me.squeeglii.mod.nanfix.NaNFixerFabric;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityHook {

    @Inject(method = "die", at = @At("HEAD"))
    private void validateOnDeath(DamageSource damageSource, CallbackInfo ci) {
        NaNFixerFabric.validateHealth(thiss());
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void validateOnTick(CallbackInfo ci) {
        NaNFixerFabric.validateHealth(thiss());
    }

    @Inject(method = "actuallyHurt", at = @At("HEAD"), cancellable = true)
    private void validateOnHurt(DamageSource damageSource, float f, CallbackInfo ci) {
        if(!thiss().isInvulnerableTo(damageSource)) return;
        if(NaNFixerFabric.validateHealth(thiss()))
            ci.cancel();
    }

    @Unique
    private LivingEntity thiss() {
        return (LivingEntity)(Object)this;
    }

}
