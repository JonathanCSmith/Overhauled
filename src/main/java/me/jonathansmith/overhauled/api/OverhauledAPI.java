package me.jonathansmith.overhauled.api;

import java.util.LinkedList;

import me.jonathansmith.overhauled.api.content.IContent;
import me.jonathansmith.overhauled.api.nexus.INexus;
import me.jonathansmith.overhauled.api.nexus.ICoreNexusProvider;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Core Overhauled API Interaction Class. All content, registries etc that wish to be automatically deployed without being built
 * against the full overhauled source should register here.
 */
public class OverhauledAPI {

    private static final LinkedList<IContent> CONTENT_LIST = new LinkedList<>();
    private static final LinkedList<INexus>   NEXUS_LIST   = new LinkedList<>();

    private static boolean hasSetRegistryManager = false;

    private static ICoreNexusProvider nexusProvider;

    /**
     * @param nexus a nexus to register for deployment
     */
    public static void registerNexus(INexus nexus) {
        OverhauledAPI.NEXUS_LIST.add(nexus);
    }

    /**
     * @return a list of all register nexus'
     */
    public static LinkedList<INexus> getNexusList() {
        return OverhauledAPI.NEXUS_LIST;
    }

    /**
     * @param content the content to register for deployment
     */
    public static void registerContent(IContent content) {
        OverhauledAPI.CONTENT_LIST.add(content);
    }

    /**
     * @return a list of all registered content
     */
    public static LinkedList<IContent> getContentList() {
        return OverhauledAPI.CONTENT_LIST;
    }

    /**
     * @param nexusProvider the nexus provider to set as the core provider for the API. This can only be set once and is used internally
     */
    public static void setNexusProvider(ICoreNexusProvider nexusProvider) {
        if (OverhauledAPI.hasSetRegistryManager) {
            return;
        }

        OverhauledAPI.hasSetRegistryManager = true;
        OverhauledAPI.nexusProvider = nexusProvider;
    }

    /**
     * @return the registry manager instance providing access to core and external registries
     */
    public static ICoreNexusProvider getNexusProvider() {
        return OverhauledAPI.nexusProvider;
    }
}
