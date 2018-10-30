package com.brackets.blocks;

import com.brackets.Brackets;

import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;


public class BlockModBlock extends BlockFurnace {

	public BlockModBlock(final String blockName, final CreativeTabs tab) {
		super(false);
		setCreativeTab(tab);
		setBlockName(this, blockName);
	}

	public BlockModBlock(final String blockName) {
		this(blockName, CreativeTabs.REDSTONE);
	}

	/**
	 * Set the registry name of {@code block} to {@code blockName} and the translation key to the full registry name.
	 *
	 * @param block     The block
	 * @param blockName The block's name
	 */
	public static void setBlockName(final Block block, final String blockName) {
		block.setRegistryName(blockName);
		block.setTranslationKey(blockName);
	}
}