package com.brackets.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.brackets.Brackets;
import com.brackets.blocks.BlockModBlock;
import com.lib.jscheme.Scheme;


public class BlockInteractive extends BlockModBlock {

	public BlockInteractive(final String blockName) {
		super(blockName);
	}

	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {
		final ItemStack heldItem = playerIn.getHeldItem(hand);

		if (!heldItem.isEmpty() && heldItem.getItem() == Items.ENDER_EYE) {
			if (!playerIn.capabilities.isCreativeMode) {
				heldItem.shrink(1);
			}

			return true;
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}
}