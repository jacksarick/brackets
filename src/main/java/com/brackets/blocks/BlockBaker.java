package com.brackets.blocks;

import com.brackets.Brackets;

import java.io.*;

import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BlockBaker extends BlockModBlock {

	public BlockBaker(String blockName) {
		super(blockName);
	}

	// @Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {

		final ItemStack heldItem = playerIn.getHeldItem(hand);


		if (!heldItem.isEmpty() && (heldItem.getItem() == Items.WRITTEN_BOOK
				|| heldItem.getItem() == Items.WRITABLE_BOOK)) {

			if (heldItem.hasTagCompound()){
				NBTTagCompound data;
				String json = "{display:{Name:\"Program Chip\",Lore:[\"This is will be cool\"]}, code:\"(define up10 (lambda (x) (+ 10 x))) (up10 410)\"}";

			// 	NBTTagCompound book = heldItem.getTagCompound();
				ItemStack output = new ItemStack(Items.EMERALD);

				try {
					data = JsonToNBT.getTagFromJson(json);
					output.setTagCompound(data);
				} catch (NBTException e) {
					System.out.println(e);
		        }

				playerIn.inventory.addItemStackToInventory(output);

				return true;

				// if (!playerIn.capabilities.isCreativeMode) {}

			}
			return false;
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}
}