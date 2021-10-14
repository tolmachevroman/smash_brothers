package com.koombea.smash.bros

import com.koombea.smash.bros.data.models.Fighter
import com.koombea.smash.bros.data.models.filterByRate
import com.koombea.smash.bros.data.models.sortByField
import org.junit.Assert
import org.junit.Test

class FiltersUnitTest {

    private val fighter1 = Fighter(
        "1", "Mario", "Universe 1", 200,
        true, 3, 3500, "", ""
    )

    private val fighter2 = Fighter(
        "2", "Kevin", "Universe 1", 230,
        true, 1, 9500, "", ""
    )

    private val fighter3 = Fighter(
        "1", "Alba", "Universe 1", 190,
        true, 5, 6500, "", ""
    )

    private val fighters: List<Fighter> = listOf(fighter1, fighter2, fighter3)

    @Test
    fun sortBy_name() {
        val sorted = fighters.sortByField(1)
        Assert.assertEquals(sorted[0].name, "Alba")
        Assert.assertEquals(sorted[1].name, "Kevin")
        Assert.assertEquals(sorted[2].name, "Mario")
    }

    @Test
    fun sortBy_price() {
        val sorted = fighters.sortByField(2)
        Assert.assertEquals(sorted[0].price, 190)
        Assert.assertEquals(sorted[1].price, 200)
        Assert.assertEquals(sorted[2].price, 230)
    }

    @Test
    fun sortBy_rate() {
        val sorted = fighters.sortByField(3)
        Assert.assertEquals(sorted[0].rate, 1)
        Assert.assertEquals(sorted[1].rate, 3)
        Assert.assertEquals(sorted[2].rate, 5)
    }

    @Test
    fun sortBy_downloads() {
        val sorted = fighters.sortByField(4)
        Assert.assertEquals(sorted[0].downloads, 3500)
        Assert.assertEquals(sorted[1].downloads, 6500)
        Assert.assertEquals(sorted[2].downloads, 9500)
    }

    @Test
    fun filterBy_rate() {
        val sorted = fighters.filterByRate(3)
        Assert.assertEquals(sorted.size, 2)
        Assert.assertEquals(sorted.contains(fighter1), true)
        Assert.assertEquals(sorted.contains(fighter3), true)
    }
}