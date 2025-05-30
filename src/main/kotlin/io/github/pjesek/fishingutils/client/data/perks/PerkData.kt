package io.github.pjesek.fishingutils.client.data.perks

data class PerkData(val id: String, val baseValue: Number, val tierMultiplier: Number) {
    var tier: Int = 0
    val totalValue: Double get() = baseValue.toDouble() + tier * tierMultiplier.toDouble()
}
