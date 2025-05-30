package io.github.pjesek.fishingutils.client.data.containers

import net.minecraft.inventory.Inventory

interface IContainer {
    fun onOpen(inventory: Inventory)
}