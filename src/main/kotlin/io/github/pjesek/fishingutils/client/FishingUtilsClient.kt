package io.github.pjesek.fishingutils.client

import net.fabricmc.api.ClientModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class FishingUtilsClient : ClientModInitializer {

    override fun onInitializeClient() {
        LOGGER.info("Client initialized!")
    }

    companion object {
        val LOGGER: Logger = LogManager.getLogger()
    }
}
