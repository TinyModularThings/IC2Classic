package ic2.api.recipes.misc;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface ICanEffect
{
	void onFoodEaten(ItemStack stack, Player player);

	@Nullable
	Component getTooltip();
	
	@OnlyIn(Dist.CLIENT)
	@Nullable
	TextureAtlasSprite getOverrideIcon();
	
	ResourceLocation getID();
}
