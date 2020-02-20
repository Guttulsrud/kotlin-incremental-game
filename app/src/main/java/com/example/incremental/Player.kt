package com.example.incremental

class Player {
    private var points:Long = 0
    private var clickStrength = 1
    private val bonusEntities = mutableSetOf<BonusEntity>()
    private var totalPoints:Long = 0
    private var totalClicks = 0


    fun clicazk(){
        this.points += clickStrength
        this.totalPoints += clickStrength
        this.totalClicks++
    }
    fun getPoints():Long{
        return this.points
    }
    fun boughtItem(price:Int){
        this.points -= price
    }
    fun addClickSrength(percentIncrease:Int){
        this.clickStrength = clickStrength * (1+(percentIncrease/100))
    }
    fun addBonusEntity(entity: BonusEntity){
        bonusEntities.add(entity)
    }
}