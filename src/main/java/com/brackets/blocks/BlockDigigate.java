package com.brackets.blocks;

import com.brackets.blocks.BlockSchemeBlock;
import com.brackets.init.ModItems;

import java.io.*;

import jscheme.InputPort;
import jscheme.Scheme;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockDigigate extends BlockSchemeBlock {

	// public static final PropertyDirection FACING = PropertyDirection.create("facing");
	// public String instructions = "(+ 400 20)";

	public BlockDigigate(final String blockName, final boolean isActive) {
		super(blockName, isActive);
	}

	public static String schemeEval(String x) {
		try {
			InputPort input = new InputPort(new StringReader(x));
  			StringWriter output = new StringWriter();
  			PrintWriter outputWriter = new PrintWriter(output, true);

			Scheme js = new Scheme(new String[] {""});
			js.load(input, outputWriter);
			return output.toString();
		} catch (Exception e) {
			System.out.println(e);
			return "Failed!";
		}
	}

	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {

		final ItemStack heldItem = playerIn.getHeldItem(hand);


		if (!heldItem.isEmpty() && heldItem.getItem() == ModItems.PROGRAM) {
			// if (!playerIn.capabilities.isCreativeMode) {}

			if (heldItem.hasTagCompound()){
				NBTTagCompound program = heldItem.getTagCompound();
				System.out.println(program.toString());
				mc.player.sendMessage(new TextComponentString("> " + schemeEval(program.getString("code"))));
				return true;
			}

			mc.player.sendMessage(new TextComponentString("That's empty"));
			return false;
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}
}