package thaumicenergistics.client.textures;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import thaumicenergistics.common.ThaumicEnergistics;

/**
 * Textures for all ThE blocks.
 *
 * @author Nividica
 *
 */
@SideOnly(Side.CLIENT)
public enum BlockTextureManager
{
		BUS_COLOR (TextureTypes.Part, new String[] { "bus.color.border", "bus.color.light", "bus.color.side" }),

		ESSENTIA_IMPORT_BUS (TextureTypes.Part, new String[] { "essentia.import.bus.face", "essentia.import.bus.overlay",
						"essentia.import.bus.chamber", "essentia.import.bus.side" }),

		ESSENTIA_LEVEL_EMITTER (TextureTypes.Part, new String[] { "essentia.level.emitter.base", "essentia.level.emitter.active",
						"essentia.level.emitter.inactive" }),

		ESSENTIA_STORAGE_BUS (TextureTypes.Part, new String[] { "essentia.storage.bus.face", "essentia.storage.bus.overlay",
						"essentia.storage.bus.side" }),

		ESSENTIA_EXPORT_BUS (TextureTypes.Part, new String[] { "essentia.export.bus.face", "essentia.export.bus.overlay",
						"essentia.export.bus.chamber", "essentia.export.bus.side" }),

		ESSENTIA_TERMINAL (TextureTypes.Part, new String[] { "essentia.terminal.overlay.dark", "essentia.terminal.overlay.medium",
						"essentia.terminal.overlay.light", "essentia.terminal.side", "essentia.termina.border" }),

		ESSENTIA_PROVIDER (TextureTypes.Block, new String[] { "essentia.provider", "essentia.provider.overlay" }),

		INFUSION_PROVIDER (TextureTypes.Block, new String[] { "infusion.provider", "infusion.provider.overlay" }),

		ARCANE_CRAFTING_TERMINAL (TextureTypes.Part, new String[] { "arcane.crafting.overlay1", "arcane.crafting.overlay2",
						"arcane.crafting.overlay3", "arcane.crafting.side", "arcane.crafting.overlay4" }),

		VIS_RELAY_INTERFACE (TextureTypes.Part, new String[] { "vis.interface", "vis.interface.runes", "vis.interface.side" }),

		GEAR_BOX (TextureTypes.Block, new String[] { "gear.box.fallback", "golem.gear.box.fallback" }),

		ESSENTIA_CELL_WORKBENCH (TextureTypes.Block, new String[] { "essentia.cell.workbench.top", "essentia.cell.workbench.bottom",
						"essentia.cell.workbench.side" }),

		GASEOUS_ESSENTIA (TextureTypes.Block, new String[] { "essentia.gas" }),

		ARCANE_ASSEMBLER (TextureTypes.Block, new String[] { "arcane.assembler.fallback" }),

		KNOWLEDGE_INSCRIBER (TextureTypes.Block,
			new String[] { "knowledge.inscriber.side", "knowledge.inscriber.top", "knowledge.inscriber.bottom" }),

		ESSENTIA_VIBRATION_CHAMBER (TextureTypes.Block, new String[] { "e.vibration.input", "e.vibration.face.off", "e.vibration.face.ignis",
						"e.vibration.face.potentia" }),

		DISTILLATION_ENCODER (TextureTypes.Block,
			new String[] { "knowledge.inscriber.side", "distillation.encoder.face", "knowledge.inscriber.bottom" });

	private enum TextureTypes
	{
			Block,
			Part;
	}

	/**
	 * Cache of the enum values
	 */
	public static final List<BlockTextureManager> ALLVALUES = Collections.unmodifiableList( Arrays.asList( BlockTextureManager.values() ) );

	private TextureTypes textureType;

	private String[] textureNames;

	private IIcon[] textures;

	private BlockTextureManager( final TextureTypes textureType, final String[] textureNames )
	{
		this.textureType = textureType;
		this.textureNames = textureNames;
		this.textures = new IIcon[this.textureNames.length];
	}

	public IIcon getTexture()
	{
		return this.textures[0];
	}

	public IIcon[] getTextures()
	{
		return this.textures;
	}

	public void registerTexture( final TextureMap textureMap )
	{
		if( textureMap.getTextureType() == 0 )
		{
			String header = ThaumicEnergistics.MOD_ID + ":";

			if( this.textureType == TextureTypes.Part )
			{
				header += "parts/";
			}

			for( int i = 0; i < this.textureNames.length; i++ )
			{
				this.textures[i] = textureMap.registerIcon( header + this.textureNames[i] );
			}
		}
	}
}
