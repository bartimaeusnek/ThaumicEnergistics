package thaumicenergistics.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import thaumicenergistics.client.textures.BlockTextureManager;
import thaumicenergistics.common.CommonProxy;
import thaumicenergistics.common.registries.Renderers;

/**
 * Client side proxy.
 *
 * @author Nividica
 *
 */
public class ClientProxy
	extends CommonProxy
{
	public ClientProxy()
	{
		MinecraftForge.EVENT_BUS.register( this );
	}
	
	@Override
	public void registerRenderers()
	{
		Renderers.registerRenderers();
	}
	
	

	@SuppressWarnings("static-method")
	@SubscribeEvent
	public void registerTextures( final TextureStitchEvent.Pre event )
	{
		// Register all block textures
		for( BlockTextureManager texture : BlockTextureManager.VALUES )
		{
			texture.registerTexture( event.map );
		}

	}
}
