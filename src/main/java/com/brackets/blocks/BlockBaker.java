package com.brackets.blocks;

import com.brackets.blocks.BlockSchemeBlock;
import com.brackets.init.ModItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBaker extends BlockSchemeBlock {

	// public static final PropertyDirection FACING = PropertyDirection.create("facing");
	// public String instructions = "(+ 400 20)";

	public BlockBaker(final String blockName, final boolean isActive) {
		super(blockName, false);
	}

	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {

		// if (!playerIn.capabilities.isCreativeMode) {}
		
		final ItemStack heldItem = playerIn.getHeldItem(hand);

		if (!heldItem.isEmpty() && (heldItem.getItem() == Items.WRITTEN_BOOK
				|| heldItem.getItem() == Items.WRITABLE_BOOK)) {

			if (heldItem.hasTagCompound()){
				NBTTagCompound book = heldItem.getTagCompound().getTagList("pages", 8).getCompoundTagAt(0);
				
				NBTTagCompound data = new NBTTagCompound();
				
				data.setString("code", book.toString());
			 	ItemStack output = new ItemStack(ModItems.PROGRAM);
				output.setTagCompound(data);

				playerIn.inventory.addItemStackToInventory(output);

				return true;

			}
			return false;
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}
}