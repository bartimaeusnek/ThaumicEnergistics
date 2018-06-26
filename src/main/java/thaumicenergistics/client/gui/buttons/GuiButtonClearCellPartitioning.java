package thaumicenergistics.client.gui.buttons;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.StatCollector;
import thaumicenergistics.client.textures.AEStateIconsEnum;

/**
 * Displays cell partitioning icon.
 *
 * @author Nividica
 *
 */
@SideOnly(Side.CLIENT)
public class GuiButtonClearCellPartitioning
	extends ThEStateButton
{

	/**
	 * Clear icon
	 */
	private static final AEStateIconsEnum CLEAR_ICON = AEStateIconsEnum.CLEAR_GRID;

	/**
	 * Creates the button
	 *
	 * @param ID
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 */
	public GuiButtonClearCellPartitioning( final int ID, final int xPosition, final int yPosition )
	{
		super( ID, xPosition, yPosition, 16, 16, GuiButtonClearCellPartitioning.CLEAR_ICON, 0, 0, AEStateIconsEnum.REGULAR_BUTTON );
	}

	@Override
	public void getTooltip( final List<String> tooltip )
	{
		// Add the info
		ThEStateButton.addAboutToTooltip( tooltip, StatCollector.translateToLocal( "gui.tooltips.appliedenergistics2.Clear" ),
			StatCollector.translateToLocal( "gui.tooltips.appliedenergistics2.ClearSettings" ) );
	}
}
