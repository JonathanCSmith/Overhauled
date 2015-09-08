package me.jonathansmith.overhauled;

import java.util.LinkedList;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import me.jonathansmith.overhauled.api.OverhauledAPI;
import me.jonathansmith.overhauled.api.content.IContent;
import me.jonathansmith.overhauled.core.CoreProperties;
import me.jonathansmith.overhauled.core.configuration.CoreConfigurationHandler;
import me.jonathansmith.overhauled.core.delegate.CommonDelegate;
import me.jonathansmith.overhauled.api.nexus.INexus;
import me.jonathansmith.overhauled.core.nexus.achievement.AchievementNexus;
import me.jonathansmith.overhauled.core.nexus.configuration.ConfigurationNexus;
import me.jonathansmith.overhauled.core.nexus.dimension.DimensionNexus;
import me.jonathansmith.overhauled.core.nexus.multiblock.MultiblockNexus;
import me.jonathansmith.overhauled.core.nexus.network.NetworkNexus;
import me.jonathansmith.overhauled.core.nexus.player.PlayerNexus;
import me.jonathansmith.overhauled.core.nexus.trait.TraitNexus;
import me.jonathansmith.overhauled.core.nexus.world.WorldNexus;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Entry point for the Overhauled mod.
 */
@Mod(
        modid = CoreProperties.ID,
        name = CoreProperties.NAME,
        version = CoreProperties.VERSION,
        guiFactory = CoreProperties.CONFIGURATION_GUI_CLASS
)
public class Overhauled {

    @Mod.Instance
    public static Overhauled instance;

    @SidedProxy(serverSide = CoreProperties.SERVER_DELEGATE_CLASS, clientSide = CoreProperties.CLIENT_DELEGATE_CLASS)
    public static CommonDelegate sidedDelegate;

    public Overhauled() {
        // Construct native nexus'
        OverhauledAPI.registerNexus(ConfigurationNexus.getInstance());
        OverhauledAPI.registerNexus(NetworkNexus.getInstance());
        OverhauledAPI.registerNexus(PlayerNexus.getInstance());
        OverhauledAPI.registerNexus(MultiblockNexus.getInstance());
        OverhauledAPI.registerNexus(TraitNexus.getInstance());
        OverhauledAPI.registerNexus(AchievementNexus.getInstance());
        OverhauledAPI.registerNexus(WorldNexus.getInstance());
        OverhauledAPI.registerNexus(DimensionNexus.getInstance());

        // Construct native content
        OverhauledAPI.registerContent(CoreConfigurationHandler.getInstance());
    }

    @Mod.EventHandler
    public void handleFingerprintViolationEvent(FMLFingerprintViolationEvent event) {
        Overhauled.sidedDelegate.handleFingerprintViolationEvent(event);
    }

    @Mod.EventHandler
    public void handlePreInitialisationEvent(FMLPreInitializationEvent event) {
        LinkedList<INexus> registries = OverhauledAPI.getNexusList();
        for (INexus registry : registries) {
            Overhauled.sidedDelegate.registerNexus(registry);
        }

        LinkedList<IContent> content = OverhauledAPI.getContentList();
        for (IContent deployableContent : content) {
            Overhauled.sidedDelegate.registerContent(deployableContent);
        }

        Overhauled.sidedDelegate.handlePreInitialisationEvent(event);
    }

    @Mod.EventHandler
    public void handleInitialisationEvent(FMLInitializationEvent event) {
        Overhauled.sidedDelegate.handleInitialisationEvent(event);
    }

    @Mod.EventHandler
    public void handlePostInitialisationEvent(FMLPostInitializationEvent event) {
        Overhauled.sidedDelegate.handlePostInitialisationEvent(event);
    }

    @Mod.EventHandler
    public void handleServerStartingEvent(FMLServerAboutToStartEvent event) {
        Overhauled.sidedDelegate.handleServerStartingEvent(event);
    }

    @Mod.EventHandler
    public void handleServerStartedEvent(FMLServerStartedEvent event) {
        Overhauled.sidedDelegate.handleServerStartedEvent(event);
    }

    @Mod.EventHandler
    public void handleServerStoppingEvent(FMLServerStoppingEvent event) {
        Overhauled.sidedDelegate.handleServerStoppingEvent(event);
    }

    @Mod.EventHandler
    public void handleServerStoppedEvent(FMLServerStoppedEvent event) {
        Overhauled.sidedDelegate.handleServerStoppedEvent(event);
    }
}
