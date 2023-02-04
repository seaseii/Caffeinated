package com.chikoritalover.caffeinated.core;

import com.chikoritalover.caffeinated.core.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Caffeinated.MODID)
public class Caffeinated {
    public static final String MODID = "caffeinated";
    private static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public Caffeinated() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModEffects.MOB_EFFECTS.register(modBus);
        ModSounds.SOUND_EVENTS.register(modBus);
        ModParticles.PARTICLE_TYPES.register(modBus);


        modBus.addListener(this::commonSetup);
        forgeBus.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }


    public static ResourceLocation getId(String id) {
        return new ResourceLocation(MODID, id);
    }
}
