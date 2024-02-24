package ic2.api.recipes.registries;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import ic2.api.recipes.ingridients.generators.IOutputGenerator;
import ic2.api.recipes.ingridients.inputs.IInput;
import ic2.api.recipes.ingridients.queue.IStackOutput;
import ic2.api.recipes.ingridients.recipes.IFluidRecipeOutput;
import ic2.api.recipes.ingridients.recipes.IRecipeOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public interface IIngredientRegistry
{
	void registerInput(ResourceLocation location, Class<? extends IInput> clz, Function<FriendlyByteBuf, IInput> creator, Function<JsonObject, IInput> serializer, @Nullable Function<Object, IInput> maker);
	
	void registerRecipeOutput(ResourceLocation location, Class<? extends IRecipeOutput> clz, Function<FriendlyByteBuf, IRecipeOutput> creator, Function<JsonObject, IRecipeOutput> serializer);
	void registerFluidOutput(ResourceLocation location, Class<? extends IFluidRecipeOutput> clz, Function<FriendlyByteBuf, IFluidRecipeOutput> creator, Function<JsonObject, IFluidRecipeOutput> serializer);
	
	void registerQueue(ResourceLocation location, Class<? extends IStackOutput> clz, Function<CompoundTag, IStackOutput> creator);
	
	void registerOutputGenerators(ResourceLocation location, Class<? extends IOutputGenerator> clz, Function<JsonObject, IOutputGenerator> creator);
	
	void writeInput(IInput input, FriendlyByteBuf buffer);
	void writeRecipeOutput(IRecipeOutput output, FriendlyByteBuf buffer);
	void writeFluidOutput(IFluidRecipeOutput output, FriendlyByteBuf buffer);
	CompoundTag writeQueue(IStackOutput queue);
	
	IInput readInput(FriendlyByteBuf buffer);
	IRecipeOutput createOutput(FriendlyByteBuf buffer);
	IFluidRecipeOutput createFluidOutput(FriendlyByteBuf buffer);
	IStackOutput readQueue(CompoundTag nbt);
	
	IInput readInput(JsonObject obj);
	IRecipeOutput readOutput(JsonObject obj);
	IFluidRecipeOutput readFluidOutput(JsonObject obj);
	IOutputGenerator readOutputGenerator(JsonObject obj);
	
	IInput createInputFrom(Object obj);
	JsonObject serializeInput(IInput input);
	JsonObject serializeOutput(IRecipeOutput output);
	JsonObject serializeFluidOutput(IFluidRecipeOutput output);
	JsonObject serializeOutputGenerator(IOutputGenerator generator);
}
