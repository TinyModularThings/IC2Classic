package ic2.api.event;

import ic2.api.tile.IWrenchable;

/** First of all, yes this class is empty. It's public documentation for the actual internal event TeBlockBakeEvent. <strong>Subscribe to the internal one not this!</strong>
 *	<p>
 *  You may be asking, why would an internal interface need documenting? Well, the answer is so people can use <code>TileEntityBlock</code>.
 *  That class is the base for all IC2 tile entities, so by being able to use that you're level to IC2.
 *  Many methods are protected in there too, as they're only designed to be called by <code>BlockTileEntity</code> (which is the block all IC2 tile entities will be on),
 *  by being able to extend it means all those methods you can now extend and be confident they will be called.
 *  </p>
 *  <p>
 *  This doesn't strictly open up <code>TileEntityBlock</code> itself though (as you could always extend that if you wanted), this opens the enum <code>TeBlock</code>.
 *  But Forge's <code>EnumHelper</code> I hear you cry! To which I reply yes, that's what the event uses. The difference is everything else you need.
 *  Sure, you can add to the enum, but you need to call the private method register, and then there's all the model code.
 *  The model code is all in <code>BlockTileEntity</code>, and if you aren't in TeBlock.values before that is called, you'll crash the renderer with your tile entity.
 *  And in the past you had to also copy IC2's <code>te.json</code> file to add the actual model location for your tile entity. That mess is now <i>all</i> gone.
 *  <br/>
 *  The event allows you to properly register your addition to <code>TeBlock</code> in a way that will be as fully supported as possible.
 *  It's not in the actual API itself as it's heavily dependent on internal classes, so you have this instead.
 *  This is probably the most documented thing in the entire IC2 API. <small>I'm too kind really. ;)</small>
 *	</p>
 *  <hr>
 *	<p>
 *	So that's all well and good, but what you really want is an example of how to do it properly. Good idea, let's go.
 *	<blockquote><pre><code>
 *	import net.minecraftforge.common.MinecraftForge;
 *	import net.minecraftforge.fml.common.Mod;
 *
 *	{@literal @}Mod(modId="ExampleIC2Addon")
 *	public class ExampleIC2Addon {
 *		public ExampleIC2Addon() {
 *			//You have to register before IC2 reaches Pre-Initialization, which unless you specifically load before it is unlikely you'll achieve.
 *			//Especially if you're an addon, you'll want to be loading after IC2 not before!
 *			//But, your mod's constructor will definitely be called before then. The issue is it's called more than once.
 *			//If you try registering the same tile entity ID twice it will crash (more on that later), so you'll have to use a singleton instance.
 *			//An enum works well for this, but anything will work as long as it's registered to the event bus once, and only once.
 *			MinecraftForge.EVENT_BUS.register(TileEntityRegisterer.INSTANCE);
 *		}
 *	}
 *
 *	import ic2.core.ref.TeBlock.TeBlockBakeEvent;
 *	import ic2.core.ref.TeBlock.DefaultDrop;
 *	import ic2.core.ref.TeBlock.HarvestTool;
 *	import ic2.core.util.Util;
 *	import net.minecraft.item.EnumRarity;
 *	import net.minecraft.util.ResourceLocation;
 *	import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
 *
 *	public enum TileEntityRegisterer {
 *		INSTANCE;
 *
 *		{@literal @}SubscribeEvent
 *		public void register(TeBlockBakeEvent event) {
 *			event.getRegisterer().add("my_machine_name", MyMachineTileEntity.class, 1000, true, Util.allFacings, true,
 *				HarvestTool.Pickaxe, DefaultDrop.Machine, 5, 10, EnumRarity.COMMON, new ResourceLocation("ExampleIC2Addon:machines"));
 *		}
 *	}
 *  </code></pre></blockquote>
 *  And that's it, you now have <code>my_machine_name</code> registered with the <code>MyMachineTileEntity</code> tile entity class, ID 1000
 *  and the model being looking for in <code>machines.json</code> blockstates file in your mod's assets.
 *  <p>
 *  <hr>
 *  <p>
 *  But hang on, what are all the parameters of <code>event.getRegisterer().add</code>? What type even is <code>event.getRegisterer()</code>?
 *  Don't worry, it's quite simple (once you know it at least).
 *	</p>
 *	<code>event.getRegisterer()</code> returns <code>ITeBlockRegisterer</code>, which is a currently simple interface to allow new <code>TeBlock</code>s to be added.
 *	<br/>
 *	The <code>add</code> method is a tad complicated, but it needs to be to pack in all the information needed. We'll go through each parameter in turn.
 *	<ul>
 *		<li><b>name</b>: The name the new enum element will be. This is important as the localisation and resource names depend on it.</li>
 *		<li><b>teClass</b>: The class of your tile entity, it must extend <code>TileEntityBlock</code> otherwise it would miss critical methods to work.
 *							If extending that is a problem then using <code>TeBlock</code> isn't what you should be doing.</li>
 *		<li><b>itemMeta</b>: The ID of the new instance. This is utterly critical, as it's the thing used to detect which <code>TeBlock</code> an item is.
 *							That's very important as the tile entity class is worked out by the ID when a block's being placed in <code>ItemBlockTileEntity</code>.
 *							Changing this will cause everyone's items to change and there is no stopping that if you do.
 *							IC2 has claimed the first 500 to prevent any immediate collisions, therefore the number you pick is somewhere between 500 and Short.MAX_VALUE.
 *							There will probably be a forum post for addons to claim sections, so I'll put a link here as soon as there is.</li>
 *		<li><b>hasActive</b>: If the tile entity has an active state too, which is based off whether <code>TileEntityBlock#getActive()</code> returns <code>true</code> or not.
 *							The models for that will be at name_active, you can check <code>te.json</code> for an example if you wish.</li>
 *		<li><b>supportedFacings</b>: A set of <code>EnumFacing</code>s that the block can face, used for rotating by the wrench for example.
 *									IC2 has some useful constants relating to that in <code>Util</code> (allFacings, horizontalFacings, downSideFacings and noFacings)</li>
 *		<li><b>allowWrenchRotating</b>: Whether the tile entity can be rotated with a wrench (for {@link IWrenchable})</li>
 *		<li><b>harvestTool</b>: The tool used to harvest the tile entity, currently only None, Pickaxe, Shovel and Axe.
 *								More can be added on request, but support has to be specifically added, so there's no nice way via <code>EnumHelper</code> unfortunately.</li>
 *		<li><b>defaultDrop</b>: The item(s) that will drop when the block is broken using the correct tool. Currently only Self, None, Generator, Machine, and AdvMachine.
 *								Like <code>harvestTool</code>, more can be added upon request.</li>
 *		<li><b>hardness</b>: The hardness the block should have with the tile entity, IC2 normally uses 5.</li>
 *		<li><b>explosionResistance</b>: The explosionResistance the block should have with the tile entity, IC2 normally uses 10.</li>
 *		<li><b>rarity</b>: The <code>EnumRarity</code> that the item will have for the tile entity.</li>
 *		<li><b>modelLocation</b>: The <code>ResourceLocation</code> to look for the blockstates, will use IC2's (<code>te.json</code>) if <code>null</code> is passed.
 *								You're very unlikely to actually want to pass null though, as the whole point of this system was to give you freedom to use your own ;)</li>
 *	</ul>
 *	<p><strong>IMPORTANT THINGS TO NOTE</strong>
 *		<dl>
 *			<dt>Translation keys</dt>
 *			<dd>Currently the translation key for your additions will be <code>ic2.te.name</code> (with name of course being the name parameter you pass in).
 *				This can change in the future if there's a demand to do so, but there is already a lot passed into the constructor and I didn't think it was a critical thing to add.</dd>
 *		</dl>
 *		More will be added to this list if questions keep re-occurring, or are about a suitably important topic.
 *	</p>
 *  <hr>
 *	<p>There, that should be everything you need to utilise <code>TeBlock</code> to add your own tile entities using IC2's very flexible framework.
 *		I'll be happy to answer any questions you have on it, your best bet is to ask me (Chocohead) on <a href="http://forum.industrial-craft.net">the IC2 forums</a>,
 *		although if you post in the support section other people might be able to help before I see your post.
 *		I'll certainly take suggestions too, this was purely to stop people having to suffer through lots of reflection and overriding of IC2, but could be so much more.
 *		<br/>
 *		<small>Goodness I spent too long on this...</small>
 *	</p>
 *
 *
 *	@see {@link ic2.core.ref.TeBlock} for how all the IC2 tile entities are registered.
 *	@see {@link ic2.core.ref.TeBlock.HarvestTool} for the currently available tools (you'll need this for adding new tile entities).
 *	@see {@link ic2.core.ref.TeBlock.DefaultDrop} for the currently available drops (you'll need this for adding new tile entities too).
 *	@see {@link ic2.core.ref.TeBlock.TeBlockBakeEvent} for the actual event that is fired (that you need to subscribe to.
 *	@see {@link ic2.core.ref.TeBlock.TeBlockBakeEvent.ITeBlockRegisterer} for the registration interface.
 *	@see {@link ic2.core.util.Util} for the useful facings you might want for <code>supportedFacings</code>.
 *	@see {@link ic2.core.block.TileEntityBlock} for the base tile entity.
 *	@see {@link ic2.core.block.BlockTileEntity} for the base block (it's final, so no extending, not that you'd ever need to).
 *	@see {@link ic2.core.item.block.ItemBlockTileEntity} for the base item.
 *
 *
 *	@since IC2 2.6.54
 *	@version 1.0
 *
 *  @author Chocohead
 */
final class TeBlockBakeEvent {
}