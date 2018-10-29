package com.brackets.items;

import net.minecraft.item.ItemWritableBook;
import net.minecraft.creativetab.CreativeTabs;


import com.brackets.Brackets;


public class ItemProgram extends ItemWritableBook {

	public ItemProgram() {
		setRegistryName(Brackets.MODID, "program");
		// setUnlocalizedName("program");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}