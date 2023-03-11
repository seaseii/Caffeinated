package com.chikoritalover.caffeinated.core;

import com.chikoritalover.caffeinated.core.data.client.ModBlockStateProvider;
import com.chikoritalover.caffeinated.core.data.client.ModItemModelProvider;
import com.chikoritalover.caffeinated.core.data.server.ModRecipeProvider;
import com.chikoritalover.caffeinated.core.misc.ModCauldronInteraction;
import com.chikoritalover.caffeinated.core.misc.ModCompostables;
import com.chikoritalover.caffeinated.core.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
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
        ModBannerPattern.BANNER_PATTERNS.register(modBus);
        ModSounds.SOUND_EVENTS.register(modBus);
        ModParticles.PARTICLE_TYPES.register(modBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modBus);

        modBus.addListener(this::commonSetup);
        modBus.addListener(this::dataSetup);
        forgeBus.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModCompostables.register();
            ModCauldronInteraction.register();
        });
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        dataGenerator.addProvider(event.includeClient(), new ModBlockStateProvider(dataGenerator, fileHelper));
        dataGenerator.addProvider(event.includeClient(), new ModItemModelProvider(dataGenerator, fileHelper));
        dataGenerator.addProvider(event.includeServer(), new ModRecipeProvider(dataGenerator));
    }

    public static ResourceLocation getId(String id) {
        return new ResourceLocation(MODID, id);
    }
}
