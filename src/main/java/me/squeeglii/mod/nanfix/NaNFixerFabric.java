package me.squeeglii.mod.nanfix;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.entity.LivingEntity;

public class NaNFixerFabric implements ModInitializer {

    @Override
    public void onInitialize() { }

    /**
     * @return should the behaviour triggering this validation be cancelled (i.e, cancel event?)
     */
    public static boolean validateHealth(LivingEntity entity) {
        if(entity == null || entity.level() == null) return false;
        if (entity.level().isClientSide) return false;

        float health = entity.getHealth();

        if (health < 0.0F) {
            entity.setHealth(0.0F);
            return false;
        }

        if(Float.isNaN(health)) {
            entity.setHealth(0.0F);
            return true;
        }

        return false;
    }
}
