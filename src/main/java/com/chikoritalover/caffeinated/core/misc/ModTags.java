package com.chikoritalover.caffeinated.core.misc;

import com.chikoritalover.caffeinated.core.Caffeinated;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;

public class ModTags {

    public static final TagKey<Block> LIT_FIRES = TagKey.create(Registry.BLOCK_REGISTRY, Caffeinated.getId("lit_fires"));

    public static final TagKey<BannerPattern> PATTERN_ITEM_JAVA = TagKey.create(Registry.BANNER_PATTERN_REGISTRY, Caffeinated.getId("pattern_item/java"));
}
