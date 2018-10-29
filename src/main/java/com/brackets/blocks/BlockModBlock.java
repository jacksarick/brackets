package com.brackets.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
// import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import com.brackets.Brackets;


public class BlockModBlock extends Block {

	public BlockModBlock(final String blockName, final CreativeTabs tab) {
		super(Material.IRON);
		// setRegistryName(Brackets.MODID, blockName);
		setCreativeTab(tab);
	}

	public BlockModBlock(final String blockName) {
		this(blockName, CreativeTabs.REDSTONE);
	}

	public static void setBlockName(int id, final String name, final Block block){
		block.setRegistryName(Brackets.MODID, name);
		final ResourceLocation registryName = Objects.requireNonNull(block.getRegistryName());
		block.setTranslationKey(registryName.toString());
		// Block.registerBlock(id, name, block);
	}

	// public static void setBlockName(final Block block, final String blockName, final Item item){
	// 	block.setRegistryName(Brackets.MODID, blockName);
	// 	block.setTranslationKey(registryName.toString());
	// 	Item.register(block, item);
	// }
}