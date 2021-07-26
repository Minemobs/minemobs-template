package fr.minemobs.minemobstemplate.mixin;

import fr.minemobs.minemobstemplate.MinemobsTemplate;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    /**
     * @author no
     */
    @Inject(method = "createTitle", at = @At("HEAD"))
    private void createTitle(CallbackInfoReturnable<String> cir) {
        MinemobsTemplate.LOGGER.info(cir.getReturnValue());
    }
}