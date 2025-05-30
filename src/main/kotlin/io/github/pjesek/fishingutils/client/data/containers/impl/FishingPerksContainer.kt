package io.github.pjesek.fishingutils.client.data.containers.impl

import io.github.pjesek.fishingutils.client.data.perks.PerkRegistry
import io.github.pjesek.fishingutils.client.data.containers.IContainer
import net.minecraft.inventory.Inventory

class FishingPerksContainer: IContainer {
    override fun onOpen(inventory: Inventory) {
        PerkRegistry.parseFromContainer(inventory)
    }
}