package com.chikoritalover.caffeinated.client;

import com.chikoritalover.caffeinated.core.Caffeinated;
import com.chikoritalover.caffeinated.core.registry.ModBlocks;
import com.chikoritalover.caffeinated.core.registry.ModParticles;
import net.minecraft.client.particle.BubblePopParticle;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Caffeinated.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CaffeinatedClient {

    @SubscribeEvent
    public static void registerParticleProviders(final RegisterParticleProvidersEvent event) {
        event.register(ModParticles.COFFEE_POP.get(), BubblePopParticle.Provider::new);
    }

    @SubscribeEvent
    @SuppressWarnings("all")
    public static void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COFFEE_SHRUB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLOWERING_COFFEE_SHRUB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_COFFEE_SHRUB.get(), RenderType.cutout());
    }
}
