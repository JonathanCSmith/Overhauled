package me.jonathansmith.overhauled.core.nexus.trait;

import java.util.NavigableSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;
import me.jonathansmith.overhauled.api.nexus.INexus;
import me.jonathansmith.overhauled.api.nexus.trait.ITrait;
import me.jonathansmith.overhauled.api.nexus.trait.ITraitHolder;
import me.jonathansmith.overhauled.api.nexus.trait.ITraitNexus;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Internal implementation of {@link ITraitNexus} that provides a nexus responsible for 'tagging'
 * objects with behaviours, traits and modifiers to mitigate the need for ASM
 */
public class TraitNexus implements ITraitNexus {

    private static TraitNexus instance;

    public static INexus getInstance() {
        if (TraitNexus.instance == null) {
            TraitNexus.instance = new TraitNexus();
        }

        return TraitNexus.instance;
    }

    private final LinkedHashMultimap<Class, ITrait> trait_map              = LinkedHashMultimap.create();
    private final ClassDepthCalculator              class_depth_calculator = new ClassDepthCalculator();

    private TraitNexus() {
    }

    @Override
    public String getUniqueIdentifier() {
        return "Trait Nexus";
    }

    @Override
    public void addTrait(Object o, ITrait t) {
        this.trait_map.put(o.getClass(), t);
    }

    @Override
    public ITrait getTrait(Object o, Class<? extends ITrait> traitClazz) {
        // First check the directly assigned
        if (o instanceof ITraitHolder) {
            ITrait t = this.getHeldTrait((ITraitHolder) o, traitClazz);
            if (t != null) {
                return t;
            }
        }

        // A list of all possible traits
        TreeMultimap<Integer, ITrait> traitChoices = TreeMultimap.create(Ordering.natural(), Ordering.usingToString());

        // Find traits from default provided class
        traitChoices = this.findTraitsForObject(traitChoices, o, traitClazz);

        // Check for an itemstack container
        if (o instanceof ItemStack) {
            Item item = ((ItemStack) o).getItem();
            if (item instanceof ITraitHolder) {
                ITrait t = this.getHeldTrait((ITraitHolder) item, traitClazz);
                if (t != null) {
                    return t;
                }
            }

            traitChoices = this.findTraitsForObject(traitChoices, item, traitClazz);
            if (item instanceof ItemBlock) {
                Block block = ((ItemBlock) item).getBlock();
                if (block instanceof ITraitHolder) {
                    ITrait t = this.getHeldTrait((ITraitHolder) block, traitClazz);
                    if (t != null) {
                        return t;
                    }

                    traitChoices = this.findTraitsForObject(traitChoices, block, traitClazz);
                }
            }
        }

        // Check for an itemblock container
        else if (o instanceof ItemBlock) {
            Block block = ((ItemBlock) o).getBlock();
            if (block instanceof ITraitHolder) {
                ITrait t = this.getHeldTrait((ITraitHolder) block, traitClazz);
                if (t != null) {
                    return t;
                }

                traitChoices = this.findTraitsForObject(traitChoices, block, traitClazz);
            }
        }

        // Take the class that is the nearest in the inheritance tree
        if (!traitChoices.isEmpty()) {
            NavigableSet<Integer> keySet = traitChoices.keySet();
            return traitChoices.get(keySet.first()).first();
        }

        return null;
    }

    private ITrait getHeldTrait(ITraitHolder holder, Class<? extends ITrait> traitClazz) {
        return holder.getTrait(traitClazz);
    }

    private TreeMultimap<Integer, ITrait> findTraitsForObject(TreeMultimap<Integer, ITrait> list, Object o, Class<? extends ITrait> traitClazz) {
        for (Class clazz : this.trait_map.keySet()) {
            if (clazz.isInstance(o)) {
                Set<ITrait> traitSet = this.trait_map.get(clazz);
                for (ITrait t : traitSet) {
                    if (traitClazz.isInstance(t)) {
                        list.put(this.class_depth_calculator.getClassDepth(o.getClass(), clazz), t);
                    }
                }
            }
        }

        return list;
    }
}
