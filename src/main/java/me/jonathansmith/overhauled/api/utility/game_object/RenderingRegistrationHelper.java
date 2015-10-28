package me.jonathansmith.overhauled.api.utility.game_object;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameData;

import com.google.common.collect.Lists;
import me.jonathansmith.overhauled.api.archetype.game_object.BlockObject;
import me.jonathansmith.overhauled.api.archetype.game_object.IStateAwareObject;
import me.jonathansmith.overhauled.core.CoreProperties;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 * <p/>
 * Implementation of IRenderingRegistrationHelper contract
 */
public class RenderingRegistrationHelper implements IRenderingRegistrationHelper {

    private static final int DEFAULT_METADATA = 0;

    @Override
    public <T extends BlockObject & IStateAwareObject> void meshMultiBlockStatesToModels(T block) {
        this.meshMultiBlockStatesToModels(block, CoreProperties.ID);
    }

    @Override
    public <T extends BlockObject & IStateAwareObject> void meshMultiBlockStatesToModels(T block, String resourceDomain) {
        final Item item = Item.getItemFromBlock(block);
        final ResourceLocation location = (ResourceLocation) GameData.getBlockRegistry().getNameForObject(block);
        final ResourceLocation targetLocation;
        if (resourceDomain.equals(location.getResourceDomain())) {
            targetLocation = new ResourceLocation(resourceDomain, location.getResourcePath());
        }

        else {
            targetLocation = location;
        }

        List<IProperty> properties = block.getProperties();
        Collection<List<Comparable>> permutations = this.buildVariantList(block);
        for (List<Comparable> permutation : permutations) {
            if (permutation.size() != properties.size()) {
                throw new IllegalStateException("A permutation of properties was generated that did not have the prerequisite number of properties");
            }

            String permutationString = "";
            IBlockState state = block.getDefaultState();
            for (int i = 0; i < properties.size(); i++) {
                IProperty property = properties.get(i);
                Comparable value = permutation.get(i);
                state.withProperty(property, value);
                permutationString += property.getName() + "=" + property.getName(value) + ",";
            }

            if (block.isValidState(state)) {
                permutationString = permutationString.substring(0, permutationString.length() - 2);
                int meta = block.getMetaFromState(state);
                ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(targetLocation, permutationString));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Collection<List<Comparable>> buildVariantList(IStateAwareObject multiModel) {
        List<IProperty> properties = multiModel.getProperties();
        List<Collection<Comparable>> collections = new LinkedList<>();
        for (IProperty property : properties) {
            collections.add((Collection<Comparable>) property.getAllowedValues());
        }

        return this.generatePermutations(collections);
    }

    private Collection<List<Comparable>> generatePermutations(List<Collection<Comparable>> collections) {
        if (collections == null || collections.isEmpty()) {
            return Collections.emptyList();
        }

        else {
            Collection<List<Comparable>> out = Lists.newLinkedList();
            recursivePermutationGenerator(collections, out, 0, new LinkedList<Comparable>());
            return out;
        }
    }

    private void recursivePermutationGenerator(List<Collection<Comparable>> origin, Collection<List<Comparable>> out, int depth, List<Comparable> current) {
        if (depth == origin.size()) {
            out.add(current);
            return;
        }

        Collection<Comparable> currentCollection = origin.get(depth);
        for (Comparable element : currentCollection) {
            List<Comparable> copy = Lists.newLinkedList(current);
            copy.add(element);
            recursivePermutationGenerator(origin, out, depth + 1, copy);
        }
    }
}
