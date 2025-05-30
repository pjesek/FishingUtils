package io.github.pjesek.fishingutils.client.data.perks

import io.github.pjesek.fishingutils.client.FishingUtilsClient.Companion.LOGGER
import net.minecraft.component.DataComponentTypes
import net.minecraft.inventory.Inventory
import net.minecraft.item.Items

object PerkRegistry {

    private val perkTypesMap = mutableMapOf<PerkType, MutableMap<String, PerkData>>()
    private val perksMap = mutableMapOf<String, PerkData>()
    fun init() {
        register(PerkType.HOOK, Perks.STRONG_HOOK, "Strong Hook", 1.0, 0.1)
        register(PerkType.HOOK, Perks.WISE_HOOK, "Wise Hook", 1.0, 0.1)
        register(PerkType.HOOK, Perks.GLIMMERING_HOOK, "Glimmering Hook", 1.0, 0.1)
        register(PerkType.HOOK, Perks.GREEDY_HOOK, "Greedy Hook", 1.0, 0.1)
        register(PerkType.HOOK, Perks.LUCKY_HOOK, "Lucky Hook", 1.0, 0.1)

        register(PerkType.MAGNET, Perks.XP_MAGNET, "XP Magnet", 0, 0.05)
        register(PerkType.MAGNET, Perks.FISH_MAGNET, "Fish Magnet", 0, 0.1)
        register(PerkType.MAGNET, Perks.PEARL_MAGNET, "Pearl Magnet", 0, 0.05)
        register(PerkType.MAGNET, Perks.TREASURE_MAGNET, "Treasure Magnet", 0, 0.05)
        register(PerkType.MAGNET, Perks.SPIRIT_MAGNET, "Spirit Magnet", 0, 0.05)

        register(PerkType.ROD, Perks.BOOSTED_ROD, "Boosted Rod", 0, 0.01)
        register(PerkType.ROD, Perks.SPEEDY_ROD, "Speedy Rod", 0, 0.01)
        register(PerkType.ROD, Perks.GRACEFUL_ROD, "Graceful Rod", 0, 0.01)
        register(PerkType.ROD, Perks.GLITCHED_ROD, "Glitched Rod", 0, 0.01)
        register(PerkType.ROD, Perks.STABLE_ROD, "Stable Rod", 0, 0.01)

        register(PerkType.POT, Perks.STRONG_POT, "Strong Pot", 1.0, 0.1)
        register(PerkType.POT, Perks.WISE_POT, "Wise Pot", 1.0, 0.1)
        register(PerkType.POT, Perks.GLIMMERING_POT, "Glimmering Pot", 1.0, 0.1)
        register(PerkType.POT, Perks.GREEDY_POT, "Greedy Pot", 1.0, 0.1)
        register(PerkType.POT, Perks.LUCKY_POT, "Lucky Pot", 1.0, 0.1)

        register(PerkType.EXTRA, Perks.ELUSIVE_CHANCE, "Elusive Chance", 0, 0.05)
/*        register(PerkType.EXTRA, Perks.WAYFINDER_DATA, "Wayfinder Data", )
        register(PerkType.EXTRA, Perks.PEARL_CHANCE, "Pearl Chance")
        register(PerkType.EXTRA, Perks.TREASURE_CHANCE, "Treasure Chance")
        register(PerkType.EXTRA, Perks.SPIRIT_CHANCE, "Spirit Chance")*/
    }

    private fun register(type: PerkType, perkEnum: Perks, key: String, baseValue: Number, tierMultiplier: Number) {
        val typeMap = perkTypesMap.getOrPut(type) { mutableMapOf() }
        val data = PerkData(perkEnum.toString(), baseValue, tierMultiplier)
        typeMap[key] = data
        perksMap[key] = data
    }

    fun getPerk(key: String): PerkData? {
        return perksMap[key]
    }

    fun parseFromContainer(inventory: Inventory) {
        for (slot in 0 until inventory.size()) {
            val stack = inventory.getStack(slot)
            if (stack.item != Items.AIR) {
                val nameComponent = stack.get(DataComponentTypes.ITEM_NAME) ?: continue
                val name = nameComponent.string

                val perk = getPerk(name.substringBefore(" (")) ?: continue
                perk.tier = name.substringAfter(" (").substringBefore("/").toIntOrNull() ?: 0
                LOGGER.info("${perk.id} | Tier: ${perk.tier}, ${perk.totalValue}")
            }
        }
    }
}