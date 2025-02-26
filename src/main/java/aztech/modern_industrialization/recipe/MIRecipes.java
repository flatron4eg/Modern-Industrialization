/*
 * MIT License
 *
 * Copyright (c) 2020 Azercoco & Technici4n
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package aztech.modern_industrialization.recipe;

import aztech.modern_industrialization.MIConfig;
import aztech.modern_industrialization.MIRuntimeResourcePack;
import aztech.modern_industrialization.materials.Material;
import aztech.modern_industrialization.materials.MaterialRegistry;
import aztech.modern_industrialization.materials.alloy.AlloyRegister;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.resource.ResourceManager;

public final class MIRecipes {
    public static RuntimeResourcePack buildRecipesPack() {
        RuntimeResourcePack pack = RuntimeResourcePack.create("modern_industrialization:recipes");

        DyeRecipes.addRecipes(pack);
        PlankRecipes.yes(pack);

        return pack;
    }

    private MIRecipes() {
    }

    public static MIRuntimeResourcePack buildGeneratedRecipesPack(ResourceManager manager) {
        MIRuntimeResourcePack pack = new MIRuntimeResourcePack("MI Generated recipes");

        AssemblerRecipes.yes(pack, manager);
        AlloyRegister.init(pack);
        HeatExchangerHelper.init(pack);

        if (MIConfig.getConfig().enableEasyMode) {
            EasyModeRecipes.yes(pack, manager);
        }

        for (Material material : MaterialRegistry.getMaterials().values()) {
            material.registerRecipes.run();
        }
        return pack;
    }
}
