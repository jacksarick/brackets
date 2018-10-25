package com.brackets.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import com.brackets.Brackets;


public class BlockModBlock extends Block {

	public BlockModBlock(final String blockName, final CreativeTabs tab) {
		super(Material.IRON);
		setRegistryName(Brackets.MODID, blockName);
		setUnlocalizedName(blockName);
		setCreativeTab(tab);
	}

	public BlockModBlock(final String blockName) {
		this(blockName, CreativeTabs.REDSTONE);
	}
}