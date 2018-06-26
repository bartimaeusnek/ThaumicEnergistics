package thaumicenergistics.client.gui.widget;

import java.util.List;
import appeng.api.storage.data.IAEItemStack;
import appeng.client.render.AppEngRenderItem;
import appeng.util.item.AEItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import thaumicenergistics.api.gui.IWidgetHost;

/**
 * Widget displaying an AE item.
 *
 * @author Nividica
 *
 * TODO: Deprecated Remove in favor of MESlots
 */
@SideOnly(Side.CLIENT)
public class WidgetAEItem
	extends ThEWidget
{
	/**
	 * Cache of the minecraft instance
	 */
	private static final Minecraft MC = Minecraft.getMinecraft();

	/**
	 * Cache of the minecraft texture manager
	 */
	private static final TextureManager TEXTURE_MANAGER = Minecraft.getMinecraft().getTextureManager();

	/**
	 * Cache of the AE item renderer
	 */
	private final AppEngRenderItem aeItemRenderer;

	/**
	 * The itemstack this widget represents
	 */
	private IAEItemStack aeItemStack;

	/**
	 * Creates the widget
	 *
	 * @param hostGUI
	 * @param xPos
	 * @param yPos
	 * @param aeItemRenderer
	 */
	public WidgetAEItem( final IWidgetHost hostGUI, final int xPos, final int yPos, final AppEngRenderItem aeItemRenderer )
	{
		super( hostGUI, xPos, yPos );

		this.aeItemRenderer = aeItemRenderer;
	}

	/**
	 * Draws the itemstack if there is one.
	 */
	@Override
	public void drawWidget()
	{
		try
		{
			if( this.aeItemStack != null )
			{
				// Set the z level
				this.zLevel = 2.0F;
				this.aeItemRenderer.zLevel = 2.0F;

				// Set the item
				this.aeItemRenderer.setAeStack( this.aeItemStack );

				// Draw the item
				this.aeItemRenderer.renderItemAndEffectIntoGUI( WidgetAEItem.MC.fontRendererObj, WidgetAEItem.TEXTURE_MANAGER,
					this.aeItemStack.getItemStack(), this.xPosition + 1, this.yPosition + 1 );

				// Draw the amount
				this.aeItemRenderer.renderItemOverlayIntoGUI( WidgetAEItem.MC.fontRendererObj, WidgetAEItem.TEXTURE_MANAGER,
					this.aeItemStack.getItemStack(), this.xPosition + 1, this.yPosition + 1 );

				// Reset the z level
				this.zLevel = 0.0F;
				this.aeItemRenderer.zLevel = 0.0F;
			}
		}
		catch( @SuppressWarnings("unused") Exception e )
		{
			// Silently ignore.
		}

	}

	/**
	 * Returns the itemstack this widget represents.
	 *
	 * @return IAEItemstack if the widget has one, null otherwise.
	 */
	public IAEItemStack getItemStack()
	{
		return this.aeItemStack;
	}

	@Override
	public void getTooltip( final List<String> tooltip )
	{
		if( this.aeItemStack != null )
		{
			// Get the stack
			ItemStack stack = this.aeItemStack.getItemStack();

			// Get the tooltip list
			try
			{
				List<String> stackTooltip = stack.getTooltip( WidgetAEItem.MC.thePlayer, WidgetAEItem.MC.gameSettings.advancedItemTooltips );

				// Set colors and add
				for( int index = 0; index < stackTooltip.size(); index++ )
				{
					if( index == 0 )
					{
						// Item name based on rarity
						stackTooltip.set( index, stack.getRarity().rarityColor + stackTooltip.get( index ) );
					}
					else
					{
						// The rest grey
						stackTooltip.set( index, EnumChatFormatting.GRAY + stackTooltip.get( index ) );
					}

					// Add the item tooltip line
					tooltip.add( stackTooltip.get( index ) );
				}
			}
			catch( @SuppressWarnings("unused") Exception e )
			{
				tooltip.add( EnumChatFormatting.ITALIC + "<Unable to get item tooltip>" );
			}

			// Get the mod name
			String modName = ( (AEItemStack)this.aeItemStack ).getModID();
			modName = modName.substring( 0, 1 ).toUpperCase() + modName.substring( 1 );

			// Add the mod name
			tooltip.add( EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + modName );
		}
	}

	@Override
	public void onMouseClicked()
	{
		// Unused
	}

	/**
	 * Set the itemstack this widget represents
	 *
	 * @param itemStack
	 */
	public void setItemStack( final IAEItemStack itemStack )
	{
		this.aeItemStack = itemStack;
	}

}
