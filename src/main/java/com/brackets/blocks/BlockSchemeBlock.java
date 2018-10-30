package com.brackets.blocks;

import jscheme.InputPort;
import jscheme.Scheme;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;


public class BlockSchemeBlock extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public Minecraft mc = Minecraft.getMinecraft();

	public BlockSchemeBlock(final String blockName, final CreativeTabs tab) {
		super(false);
		setCreativeTab(tab);
		setBlockName(this, blockName);
	}

	public BlockSchemeBlock(final String blockName) {
		this(blockName, CreativeTabs.REDSTONE);
	}

	/**
	 * Set the registry name of {@code block} to {@code blockName} and the translation key to the full registry name.
	 *
	 * @param block     The block
	 * @param blockName The block's name
	 */
	public static void setBlockName(final Block block, final String blockName) {
		block.setRegistryName(blockName);
		block.setTranslationKey(blockName);
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


		if (!heldItem.isEmpty() && heldItem.getItem() == Items.EMERALD) {
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