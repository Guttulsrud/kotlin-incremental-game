package com.example.incremental

class Player {
    private var points:Long = 0
    private var clickStrength = 1
    private val bonusEntities = mutableSetOf<BonusEntity>()
    @kotlin.ExperimentalUnsignedTypes
    private var totalPoints:ULong = 0.toULong()
    private var totalClicks = 0

    fun click(){
        this.points += clickStrength + this.getBonusEntities().elementAt(0).getBonus()

        this.totalPoints += clickStrength.toUInt()
        this.totalClicks++
    }
    fun getPoints():Long{
        return this.points
    }
    fun boughtItem(price:Int){
        this.points -= price
    }
    fun addClickSrength(increase:Int){
        this.clickStrength += increase
    }
    fun addBonusEntity(entity: BonusEntity){
        bonusEntities.add(entity)
    }
    fun getBonusEntities(): Set<BonusEntity> {
        return this.bonusEntities
    }
}