package ic2.api.recipes.ingridients.inputs;

import com.google.gson.JsonObject;

public interface INullableInput extends IInput
{
	@Override
	default JsonObject serialize() { return new JsonObject(); }
}
