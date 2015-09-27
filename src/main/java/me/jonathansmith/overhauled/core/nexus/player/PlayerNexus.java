package me.jonathansmith.overhauled.core.nexus.player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import me.jonathansmith.overhauled.api.delegate.event.CommonPreInitialisationEvent;
import me.jonathansmith.overhauled.api.nexus.player.*;
import me.jonathansmith.overhauled.core.log.LogHandler;
import me.jonathansmith.overhauled.core.nexus.network.NetworkNexus;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Implementation of IPlayerRegistry that provides point of access for player associated methods and for custom player
 * extensions
 */
public class PlayerNexus implements IPlayerNexus {

    private static PlayerNexus instance;

    public static PlayerNexus getInstance() {
        if (PlayerNexus.instance == null) {
            PlayerNexus.instance = new PlayerNexus();
        }

        return PlayerNexus.instance;
    }

    private final EventBus                                    player_event_bus = new EventBus();
    private final ArrayList<Class<? extends IExtendedPlayer>> player_extenders = new ArrayList<>();

    private PlayerNexus() {}

    @Override
    public String getUniqueIdentifier() {
        return "Player Nexusw";
    }

    @Override
    public void registerPlayerExtender(Class<? extends IExtendedPlayer> extendedPlayerClass) {
        this.player_extenders.add(extendedPlayerClass);
    }

    @Override
    public IExtendedPlayer getExtendedPlayer(EntityPlayer thePlayer, Class<? extends IExtendedPlayer> playerExtender) {
        if (thePlayer == null || playerExtender == null) {
            return null;
        }

        return (IExtendedPlayer) thePlayer.getExtendedProperties(playerExtender.getCanonicalName());
    }

    @Override
    public void registerPlayerEventListener(IPlayerEventListener playerEventListener) {
        this.player_event_bus.register(playerEventListener);
    }

    @SubscribeEvent
    public void handlePreInitialisationEvent(CommonPreInitialisationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
        NetworkNexus.getInstance().registerPacket(new PlayerPacketHandler(this.player_event_bus));
    }

    @SubscribeEvent
    public void onPlayerConstructing(EntityEvent.EntityConstructing event) {
        if (!(event.entity instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer thePlayer = (EntityPlayer) event.entity;
        for (Class<? extends IExtendedPlayer> clazz : this.player_extenders) {
            IExtendedPlayer extendedPlayer = this.constructExtendedPlayer(clazz);
            thePlayer.registerExtendedProperties(clazz.getCanonicalName(), extendedPlayer);
        }
    }

    @SubscribeEvent
    public void onPlayerJoinGame(PlayerEvent.PlayerLoggedInEvent event) {
        NetworkNexus.getInstance().sendTo(new PlayerPacket(PlayerEventType.JOIN), (EntityPlayerMP) event.player);
        this.player_event_bus.post(new PlayerRegistryEvent.PlayerJoinEvent(Side.SERVER, event.player));
    }

    @SubscribeEvent
    public void onPlayerLeaveGame(PlayerEvent.PlayerLoggedOutEvent event) {
        NetworkNexus.getInstance().sendTo(new PlayerPacket(PlayerEventType.LEAVE), (EntityPlayerMP) event.player);
        this.player_event_bus.post(new PlayerRegistryEvent.PlayerLeaveEvent(Side.SERVER, event.player));
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        NetworkNexus.getInstance().sendTo(new PlayerPacket(PlayerEventType.RESPAWN), (EntityPlayerMP) event.player);
        this.player_event_bus.post(new PlayerRegistryEvent.PlayerRespawnEvent(Side.SERVER, event.player));
    }

    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        NetworkNexus.getInstance().sendTo(new PlayerPacket(PlayerEventType.CHANGE_DIMENSION, event.fromDim, event.toDim), (EntityPlayerMP) event.player);
        this.player_event_bus.post(new PlayerRegistryEvent.PlayerChangeDimensionEvent(Side.SERVER, event.player, event.fromDim, event.toDim));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        switch (event.phase) {
            case START:
                this.player_event_bus.post(new PlayerRegistryEvent.PlayerTickStartEvent(event.side, event.player));
                break;

            case END:
                this.player_event_bus.post(new PlayerRegistryEvent.PlayerTickEndEvent(event.side, event.player));
                break;
        }
    }

    private IExtendedPlayer constructExtendedPlayer(Class<? extends IExtendedPlayer> clazz) {
        IExtendedPlayer player;
        try {
            Constructor constructor = clazz.getConstructor();
            player = (IExtendedPlayer) constructor.newInstance();
        }

        catch (NoSuchMethodException ex) {
            LogHandler.getInstance().error("Could not instantiate extended player");
            return null;
        }

        catch (InstantiationException ex) {
            LogHandler.getInstance().error("Could not instantiate extended player");
            return null;
        }

        catch (InvocationTargetException ex) {
            LogHandler.getInstance().error("Could not instantiate extended player");
            return null;
        }

        catch (IllegalAccessException ex) {
            LogHandler.getInstance().error("Could not instantiate extended player");
            return null;
        }

        return player;
    }
}
