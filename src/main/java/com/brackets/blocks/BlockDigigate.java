package com.brackets.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import com.brackets.blocks.BlockModBlock;

import jscheme.Scheme;
import java.io.*;

public class BlockDigigate extends BlockModBlock {

	public Minecraft mc = Minecraft.getMinecraft();
	// public String instructions = "(+ 400 20)";

	public BlockDigigate(final String blockName) {
		super(blockName);
	}

	public static String schemeEval(String x) {
		Scheme js = new Scheme(new String[] {"config.scm"});
		try {
			return (String) js.eval(x);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return "Failed: " + x + " with: " + sw.toString();
		}
	}

	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {

		final ItemStack heldItem = playerIn.getHeldItem(hand);


		if (!heldItem.isEmpty() && heldItem.getItem() == Items.EMERALD) {
			// if (!playerIn.capabilities.isCreativeMode) {}

			if (heldItem.hasTagCompound()){
				NBTTagCompound program = heldItem.getTagCompound();
				mc.player.sendMessage(new TextComponentString(schemeEval(program.getString("code"))));
				return true;
			}

			mc.player.sendMessage(new TextComponentString("That's empty"));
			return false;
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}
}