package me.squeeglii.mod.nanfix.mixin;

import me.squeeglii.mod.nanfix.NaNFixerFabric;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerHook {

    @Inject(method = "die", at = @At("HEAD"))
    private void validateOnDeath(DamageSource damageSource, CallbackInfo ci) {
        NaNFixerFabric.validateHealth(thiss());
    }

    @Unique
    private LivingEntity thiss() {
        return (LivingEntity)(Object)this;
    }

}
