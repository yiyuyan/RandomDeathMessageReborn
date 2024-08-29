package cn.ksmcbrigade.rdmr.mixin;

import cn.ksmcbrigade.rdmr.RandomDeathMessageReborn;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(DamageSource.class)
public class DamageSourceMixin {
    /**
     * @author KSmc_brigade
     * @reason get the custom death message
     */
    @Overwrite
    public Component getLocalizedDeathMessage(LivingEntity entity) {
        String message = RandomDeathMessageReborn.MESSAGES.get(ThreadLocalRandom.current().nextInt(0, RandomDeathMessageReborn.MESSAGES.size())).replaceAll("<rdmr[.]player[.]name>",entity.getDisplayName().getString());
        return Component.literal(message);
    }
}
