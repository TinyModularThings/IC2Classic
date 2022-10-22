package ic2.api.recipes.misc;

import java.util.Collection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SimpleCanEffect implements ICanEffect
{
	final ResourceLocation id;
	final MobEffectInstance[] effects;
	Component component = null;
	
	ResourceLocation texture;
	
	boolean isRandom = false;
	float chance;
	
	public SimpleCanEffect(ResourceLocation id, Collection<MobEffectInstance> effects)
	{
		this(id, effects.toArray(new MobEffectInstance[effects.size()]));
	}
	
	public SimpleCanEffect(ResourceLocation id, MobEffectInstance... effects)
	{
		this.id = id;
		this.effects = effects;
	}
	
	public SimpleCanEffect setRandom(float chance)
	{
		this.chance = chance;
		isRandom = true;
		return this;
	}
	
	/**
	 * @return the path to the exact texture. 
	 */
	public SimpleCanEffect setTexture(ResourceLocation texture)
	{
		this.texture = texture;
		return this;
	}
	
	public SimpleCanEffect setTooltip(Component component)
	{
		this.component = component;
		return this;
	}
	
	@Override
	public void onFoodEaten(ItemStack stack, Player player)
	{
		if(player.level.isClientSide)
		{
			return;
		}
		if((isRandom && player.level.random.nextFloat() >= chance) || effects == null || effects.length == 0)
		{
			return;
		}
		for(int i = 0;i < effects.length;i++)
		{
			if(effects[i] == null)
			{
				continue;
			}
			player.addEffect(new MobEffectInstance(effects[i]));
		}
	}
	
	@Override
	public Component getTooltip()
	{
		return component;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public TextureAtlasSprite getOverrideIcon()
	{
		return texture == null ? null : Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(texture);
	}
	
	@Override
	public ResourceLocation getID()
	{
		return id;
	}
	
}
