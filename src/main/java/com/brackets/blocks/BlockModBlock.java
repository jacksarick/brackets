package com.brackets.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.brackets.Brackets;


public class BlockModBlock extends Block {

	public BlockModBlock(String blockName) {
		super(Material.IRON);
		setRegistryName(Brackets.MODID, blockName);
		setUnlocalizedName(this.getRegistryName().toString());
	}
}