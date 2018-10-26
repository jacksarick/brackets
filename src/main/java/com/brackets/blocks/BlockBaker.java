package com.brackets.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BlockBaker extends BlockModBlock {

	public BlockBaker(final String blockName) {
		super(blockName);
	}

	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {

		final ItemStack heldItem = playerIn.getHeldItem(hand);


		if (!heldItem.isEmpty() && (heldItem.getItem() == Items.WRITTEN_BOOK
				|| heldItem.getItem() == Items.WRITABLE_BOOK)) {

			if (heldItem.hasTagCompound()){
				NBTTagCompound book = heldItem.getTagCompound();
				ItemStack output = new ItemStack(Items.EMERALD);
				NBTTagCompound program = new NBTTagCompound();

				program.setString("name", "PROGRAM");
				program.setString("desc", book.getString("title"));
				program.setString("code", book.getTag("pages").toString());
				output.setTagCompound(program);


				playerIn.inventory.addItemStackToInventory(output);

				return true;

				// if (!playerIn.capabilities.isCreativeMode) {}

			}
			return false;
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}
}