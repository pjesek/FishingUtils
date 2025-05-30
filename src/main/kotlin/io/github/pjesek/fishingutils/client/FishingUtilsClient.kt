package io.github.pjesek.fishingutils.client

import io.github.pjesek.fishingutils.client.data.perks.PerkRegistry
import io.github.pjesek.fishingutils.client.data.containers.ContainerManager
import io.github.pjesek.fishingutils.client.data.containers.impl.FishingPerksContainer
import net.fabricmc.api.ClientModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class FishingUtilsClient : ClientModInitializer {

    override fun onInitializeClient() {
        PerkRegistry.init()
        val containerManager = ContainerManager()
        containerManager.register("FISHING PERKS", FishingPerksContainer())

        LOGGER.info("Client initialized!")
    }

    companion object {
        val LOGGER: Logger = LogManager.getLogger()
    }
}
