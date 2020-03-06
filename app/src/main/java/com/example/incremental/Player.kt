package com.example.incremental

class Player {
    private var points: Int = 0
    private var clickStrength = 1
    private val bonusEntities = mutableSetOf<BonusEntity>()
    private var totalPoints: Int = 0
    private var totalClicks = 0

    fun click() {
        this.points += clickStrength + this.getBonusEntities().elementAt(0).getBonus()

        this.totalPoints += clickStrength
        this.totalClicks++
    }

    fun getPoints(): Int {
        return this.points
    }

    fun getTotalPoints(): Int {
        return this.totalPoints
    }

    fun getTotalClicks(): Int {
        return this.totalClicks
    }

    fun getClickStrength(): Int {
        return this.clickStrength
    }


    fun boughtItem(price: Int) {
        this.points -= price
    }

    fun increaseClickStrength(increaseWith: Int) {
        this.clickStrength += increaseWith
    }

    fun addBonusEntity(entity: BonusEntity) {
        bonusEntities.add(entity)
    }

    fun getBonusEntities(): Set<BonusEntity> {
        return this.bonusEntities
    }

    fun updateFromDb(points:Int, strength: Int, totalPoints: Int, totalClicks: Int) {
        this.points = points
        this.clickStrength = strength
        this.totalPoints = totalPoints
        this.totalClicks = totalClicks
    }
}