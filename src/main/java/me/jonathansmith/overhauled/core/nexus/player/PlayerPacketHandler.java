package me.jonathansmith.overhauled.core.nexus.player;

import me.jonathansmith.overhauled.api.nexus.network.ReceivingSide;
import me.jonathansmith.overhauled.api.nexus.player.PlayerEventType;
import me.jonathansmith.overhauled.api.archetype.network.AbstractPacketManager;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 * <p/>
 * {@link me.jonathansmith.overhauled.api.nexus.player.IPlayerNexus}'s personal packet for syncing client and server player information
 */
public class PlayerPacketHandler extends AbstractPacketManager<PlayerPacket> {

    public PlayerPacketHandler() {
        super(PlayerPacket.class, ReceivingSide.BOTH);
    }

    @Override
    public PlayerPacket getMessageReplyFromClient(final PlayerPacket message) {
        switch (message.getPlayerEventType()) {
            case JOIN:
                return new PlayerPacket(PlayerEventType.JOIN);

            case LEAVE:
                return new PlayerPacket(PlayerEventType.LEAVE);

            case RESPAWN:
                return new PlayerPacket(PlayerEventType.RESPAWN);

            case CHANGE_DIMENSION:
                return new PlayerPacket(PlayerEventType.CHANGE_DIMENSION);
        }

        return null;
    }

    @Override
    public PlayerPacket getMessageReplyFromServer(final PlayerPacket message) {
        return null;
    }
}
