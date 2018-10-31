package com.brackets.blocks;

import java.util.Random;

import com.brackets.blocks.BlockSchemeBlock;
import com.brackets.init.ModItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBaker extends BlockSchemeBlock {

	public Boolean hasDisk = false;

	public BlockBaker(final String blockName) {
		super(blockName);
	}

	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {

		final ItemStack heldItem = playerIn.getHeldItem(hand);

		if (!heldItem.isEmpty()) {
			//Take a disk, if needed
			if (heldItem.getItem() == ModItems.PROGRAM && !this.hasDisk) {
				if (!playerIn.capabilities.isCreativeMode) {
					heldItem.shrink(1);
				}
				this.hasDisk = true;
				return true;
			}
			
//			Otherwise, try to make one
			if (heldItem.hasTagCompound() && this.hasDisk) {
				NBTTagCompound data = new NBTTagCompound();
				NBTTagCompound tooltip = new NBTTagCompound();
				
				//In Creative, you can write directly from book and quill
				if(heldItem.getItem() == Items.WRITABLE_BOOK && playerIn.capabilities.isCreativeMode) {
					NBTTagList book = heldItem.getTagCompound().getTagList("pages", 8);
					
					data.setString("code", book.getStringTagAt(0));
					tooltip.setString("Name", "Burned ROM Disk");
					data.setTag("display", tooltip);
				 	ItemStack output = new ItemStack(ModItems.PROGRAM);
					output.setTagCompound(data);

					playerIn.inventory.addItemStackToInventory(output);
					this.hasDisk = false;

					return true;
				}
			}
		}
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}
	
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){
        if (this.hasDisk){
            worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.0D, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
        }
    }
}
			
			
//			if(heldItem.getItem() == Items.WRITTEN_BOOK) {
//				NBTTagCompound book = heldItem.getTagCompound();
//				NBTTagList pages = book.getTagList("pages", 8);
//				
//				//Add page data
//				String code = "";
//				for (int i = 0 ; i < pages.tagCount(); i++) {
//					code += pages.getStringTagAt(i);
//				}
//				data.setString("code", code);
//				
//				
//				//Try for more information
//				try {
//					String json = "{display:{Name:\"" + book.getString("title") + "\", Lore:['Burned ROM Disk']}}";
//					data = JsonToNBT.getTagFromJson(json);
//					data.setTag("display", data);
//				} catch (NBTException e) {
//					System.out.println(e);
//		        }
//				
//				ItemStack output = new ItemStack(ModItems.PROGRAM);
//				output.setTagCompound(data);
//				playerIn.inventory.addItemStackToInventory(output);
//			}