package com.brackets.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
// import net.minecraftforge.fml.common.Mod.EventHandler;
// import net.minecraftforge.fml.common.registry.ForgeRegistries;

import com.brackets.Brackets;


public class ModBlock extends Block {

	public ModBlock(String blockName) {
		super(Material.IRON);
		setUnlocalizedName(blockName);
		setRegistryName(Brackets.MODID, blockName);
		// this.setBlockTextureName( + ":" + blockName);
	}
}