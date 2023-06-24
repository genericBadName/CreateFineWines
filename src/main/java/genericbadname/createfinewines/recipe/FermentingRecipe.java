package genericbadname.createfinewines.recipe;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.recipe.RecipeFinder;
import genericbadname.createfinewines.register.content.ModRecipes;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.List;

public class FermentingRecipe extends ProcessingRecipe<RecipeWrapper> {
    public FermentingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(ModRecipes.FERMENTING, params);
    }


    @Override
    protected int getMaxInputCount() {
        return 1;
    }

    @Override
    protected int getMaxFluidInputCount() {
        return 1;
    }

    @Override
    protected int getMaxFluidOutputCount() {
        return 1;
    }

    @Override
    protected int getMaxOutputCount() {
        return 1;
    }

    @Override
    public boolean matches(RecipeWrapper pContainer, Level pLevel) {
        if (pContainer.isEmpty()) return false;
        return ingredients.get(0).test(pContainer.getItem(0));

    }

    public static boolean matches(FluidStack fluid,Level level)
    {
        List<Recipe<?>> list = RecipeFinder.get(null, level, (recipe -> recipe.getType() == ModRecipes.FERMENTING.getType()));
        list= list.stream()
                .filter((recipe -> {
                    if(recipe instanceof FermentingRecipe ferment)
                    {
                        return ferment.getFluidIngredients().get(0).test(fluid);
                    }
                    return false;
                })).toList();
        return list.size()==1;
    }

    public static Fluid getResult(FluidStack fluid,Level level)
    {
        List<Recipe<?>> list = RecipeFinder.get(null, level, (recipe -> recipe.getType() == ModRecipes.FERMENTING.getType()));
        list= list.stream()
                .filter((recipe -> {
                    if(recipe instanceof FermentingRecipe ferment)
                    {
                        return ferment.getFluidIngredients().get(0).test(fluid);
                    }
                    return false;
                })).toList();
        return ((FermentingRecipe) list.get(0)).getFluidResults().get(0).getFluid();
    }

}
