package thaumicenergistics.common.items;

import com.google.common.base.Optional;
import appeng.api.config.PowerMultiplier;
import appeng.items.tools.powered.powersink.AEBasePoweredItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumicenergistics.api.IThEWirelessEssentiaTerminal;
import thaumicenergistics.api.ThEApi;
import thaumicenergistics.common.ThaumicEnergistics;
import thaumicenergistics.common.registries.ThEStrings;

// Note to fix inconsistent hierarchy: Include the COFHCore & IC2 Api's into
// build path
/**
 * Provides wireless access to networked essentia.
 *
 * @author Nividica
 *
 */
public class ItemWirelessEssentiaTerminal
	extends AEBasePoweredItem
	implements IThEWirelessEssentiaTerminal
{
	/**
	 * NBT keys
	 */
	private static final String NBT_AE_SOURCE_KEY = "securityKey";

	/**
	 * Amount of power the wireless terminal can store.
	 */
	private static final int POWER_STORAGE = 1600000;

	/**
	 * Used during power calculations.
	 */
	public static double GLOBAL_POWER_MULTIPLIER = PowerMultiplier.CONFIG.multiplier;

	/**
	 * Creates the wireless terminal item.
	 */
	public ItemWirelessEssentiaTerminal()
	{
		super( POWER_STORAGE, Optional.<String> absent() );
		
	}

	/**
	 * Gets or creates the NBT compound tag for the terminal.
	 *
	 * @param wirelessTerminal
	 * @param the source key
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static NBTTagCompound getOrCreateCompoundTag( final ItemStack wirelessTerminal, final String sourceKey)
	{
		NBTTagCompound dataTag = new NBTTagCompound();

		// Ensure the terminal has a tag
		if( !wirelessTerminal.hasTagCompound() )
		{
			dataTag.setString( ItemWirelessEssentiaTerminal.NBT_AE_SOURCE_KEY, sourceKey );
			// Create a new tag.
			wirelessTerminal.setTagCompound( ( dataTag ) );
			
		}
		else
		{
			// Get the tag
			dataTag = wirelessTerminal.getTagCompound();
		}

		return dataTag;
	}

	/**
	 * Gets the encryption, or source, key for the specified terminal.
	 *
	 * @param wirelessTerminal
	 * @return
	 */
	@Override
	public String getEncryptionKey( final ItemStack wirelessTerminal )
	{
		String key = "";
		// Ensure the terminal has a tag
		if( wirelessTerminal.hasTagCompound() )
		{
			// Get the security terminal source key
			NBTTagCompound sourceKey = wirelessTerminal.getTagCompound();
				if (sourceKey != null)
			key = sourceKey.getString( ItemWirelessEssentiaTerminal.NBT_AE_SOURCE_KEY );

			// Ensure the source is not empty nor null
			if( ( key != null ) && ( !key.isEmpty() ) ){
				// The terminal is linked.
				return key;
			}
		}
		// Terminal is unlinked.
		return "";
	}

	@Override
	public String getUnlocalizedName()
	{
		return ThEStrings.Item_WirelessEssentiaTerminal.getUnlocalized();
	}

	@Override
	public String getUnlocalizedName( final ItemStack itemStack )
	{
		return ThEStrings.Item_WirelessEssentiaTerminal.getUnlocalized();
	}

	/**
	 * Gets the data tag used to save the terminal settings a power level.
	 */
	@Override
	public NBTTagCompound getWETerminalTag( final ItemStack wirelessTerminal )
	{
		
		return wirelessTerminal.getTagCompound();//this.getOrCreateCompoundTag( wirelessTerminal,null );
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFull3D()
	{
		return false;
	}

	/**
	 * Opens the wireless terminal.
	 *
	 * @param itemStack
	 * @param world
	 * @param entityPlayer
	 * @return
	 */
	@Override
	public ItemStack onItemRightClick( final ItemStack itemStack, final World world, final EntityPlayer player )
	{
		// Open the gui
		ThEApi.instance().interact().openWirelessTerminalGui( player );

		return itemStack;

	}

	/**
	 * Registers and sets the wireless terminal icon.
	 */
	@Override
	public void registerIcons( final IIconRegister iconRegister )
	{
		this.itemIcon = iconRegister.registerIcon( ThaumicEnergistics.MOD_ID + ":wireless.essentia.terminal" );
	}

	/**
	 * Sets the encryption, or source, key for the specified terminal.
	 *
	 * @param wirelessTerminal
	 * @param sourceKey
	 * @param name
	 * Ignored.
	 */
	@Override
	public void setEncryptionKey( final ItemStack wirelessTerminal, final String sourceKey, final String name )
	{
		// Set the key
		NBTTagCompound data = wirelessTerminal.getTagCompound();
		if (data == null)
			data = new NBTTagCompound();
		data.setString(ItemWirelessEssentiaTerminal.NBT_AE_SOURCE_KEY, sourceKey);
		wirelessTerminal.setTagCompound(data);
		//this.getOrCreateCompoundTag( wirelessTerminal,sourceKey );
	}

	/**
	 * Always show the durability bar.
	 *
	 * @param wirelessTerminal
	 * @return
	 */
	@Override
	public boolean showDurabilityBar( final ItemStack wirelessTerminal )
	{
		return true;
	}
	
}
