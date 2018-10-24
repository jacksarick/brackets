package com.brackets.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
// import net.minecraftforge.fml.common.Mod.EventHandler;
// import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class ModBlock extends Block {

	public ModBlock(String blockName) {
		super(Material.IRON);
		this.setUnlocalizedName(blockName);
		this.setRegistryName(blockName);
		// this.setBlockTextureName( + ":" + blockName);
	}
}