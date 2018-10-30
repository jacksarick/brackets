package com.brackets.items;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;


import com.brackets.Brackets;


public class ItemProgram extends Item {

	public ItemProgram() {
		setRegistryName("program");
		setTranslationKey(this.getRegistryName().toString());
		setCreativeTab(CreativeTabs.REDSTONE);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		BlockPos trace = Minecraft.getMinecraft().objectMouseOver.getBlockPos();
		if(trace != null){
			System.out.println(worldIn.getBlockState(trace).getBlock().getRegistryName());

		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
}