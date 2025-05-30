package io.github.pjesek.fishingutils.client.data.containers

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen
import net.minecraft.inventory.Inventory

class ContainerManager {

    private val containerRegistry = mutableMapOf<String, IContainer>()
    private var previous: Screen? = null

    init {
        ClientTickEvents.END_CLIENT_TICK.register(this::tick)
    }

    fun register(title: String, container: IContainer) {
        containerRegistry[title] = container
    }

    fun tick(client: MinecraftClient) {
        val screen = client.currentScreen
        if (screen == null || screen == previous) {
            return
        }
        previous = screen
        if (screen !is GenericContainerScreen) {
            return
        }
        val inventory = screen.screenHandler.inventory
        open(inventory, screen.title.string)
    }

    private fun open(inventory: Inventory, title: String) {
        for (entry in containerRegistry) {
            val container = entry.value
            if(title.contains(entry.key)) {
                container.onOpen(inventory)
            }
        }
    }
}