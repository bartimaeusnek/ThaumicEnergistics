package thaumicenergistics.common.items;

import java.util.List;
import org.lwjgl.input.Keyboard;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumicenergistics.api.items.IRestrictedEssentiaContainerItem;
import thaumicenergistics.api.storage.IAspectStack;
import thaumicenergistics.common.registries.ThEStrings;
import thaumicenergistics.common.storage.AspectStack;
import thaumicenergistics.common.tiles.TileEssentiaVibrationChamber;
import thaumicenergistics.common.tiles.abstraction.TileEVCBase;

/**
 * {@link TileEssentiaVibrationChamber} item.
 *
 * @author Nividica
 *
 */
public class ItemBlockEssentiaVibrationChamber
	extends ItemBlock
	implements IRestrictedEssentiaContainerItem
{
	public ItemBlockEssentiaVibrationChamber( final Block block )
	{
		super( block );
	}

	/**
	 * Gets the aspect stack from the EVC item.
	 *
	 * @param evcStack
	 * @return AspectStack or null
	 */
	private static IAspectStack getStoredAspectStack( final ItemStack evcStack )
	{
		// Get the tag
		NBTTagCompound data = evcStack.getTagCompound();

		// Is there essentia stored?
		if( ( data == null ) || ( !data.hasKey( TileEVCBase.NBTKEY_STORED ) ) )
		{
			return null;
		}

		return AspectStack.loadAspectStackFromNBT( data.getCompoundTag( TileEVCBase.NBTKEY_STORED ) );
	}

	private static void setStoredAspectStack( final ItemStack evcStack, final IAspectStack aspectStack )
	{
		// Get the tag
		NBTTagCompound data = evcStack.getTagCompound();
		if( data == null )
		{
			data = new NBTTagCompound();
		}

		// Is the stack empty?
		if( aspectStack == null )
		{
			// Remove the stored data
			data.removeTag( TileEVCBase.NBTKEY_STORED );
		}
		else
		{
			// Create the subtag
			NBTTagCompound storedTag = new NBTTagCompound();

			// Write the aspect stack into it
			aspectStack.writeToNBT( storedTag );

			// Write the stored tag
			data.setTag( TileEVCBase.NBTKEY_STORED, storedTag );
		}

		// Is the data tag empty?
		if( data.hasNoTags() )
		{
			// Set to null
			data = null;
		}

		// Set tag
		evcStack.setTagCompound( data );
	}

	@Override
	public boolean acceptsAspect( final Aspect aspect )
	{
		return TileEVCBase.acceptsAspect( aspect );
	}

	@Override
	public void addInformation( final ItemStack evcStack, final EntityPlayer player, final List displayList, final boolean advancedItemTooltips )
	{
		// Ignore stacks without a tag
		if( !evcStack.hasTagCompound() )
		{
			return;
		}

		// Is shift being held?
		if( Keyboard.isKeyDown( Keyboard.KEY_LSHIFT ) || ( Keyboard.isKeyDown( Keyboard.KEY_RSHIFT ) ) )
		{

			// Load the stack
			IAspectStack storedEssentia = ItemBlockEssentiaVibrationChamber.getStoredAspectStack( evcStack );

			// Add stored info
			if( storedEssentia != null )
			{
				displayList.add( String.format( "%s x %d", storedEssentia.getAspectName(), storedEssentia.getStackSize() ) );
			}
		}
		else
		{
			// Let the user know they can hold shift
			displayList.add( EnumChatFormatting.WHITE.toString() + EnumChatFormatting.ITALIC.toString() +
							ThEStrings.Tooltip_ItemStackDetails.getLocalized() );
		}

	}

	@Override
	public AspectList getAspects( final ItemStack evcStack )
	{
		AspectList list = new AspectList();

		// Ignore stacks without a tag
		if( evcStack.hasTagCompound() )
		{
			// Get the stored aspect
			IAspectStack stored = ItemBlockEssentiaVibrationChamber.getStoredAspectStack( evcStack );
			if( ( stored != null ) && !stored.isEmpty() )
			{
				// Add it
				list.add( stored.getAspect(), (int)stored.getStackSize() );
			}

		}

		return list;
	}

	@Override
	public void setAspects( final ItemStack evcStack, final AspectList list )
	{
		IAspectStack aspectStack = null;

		if( list.size() > 0 )
		{
			Aspect aspect = list.getAspects()[0];

			// Create the aspect stack
			aspectStack = new AspectStack( aspect, list.getAmount( aspect ) );
		}

		// Set the tag
		ItemBlockEssentiaVibrationChamber.setStoredAspectStack( evcStack, aspectStack );
	}
}
