package com.example.incremental

class Player {
    private var points:Long = 0
    private var clickStrength = 1
    private interface bonusEntities<BonusEntity> : Collection<BonusEntity>
    private var totalPoints:Long = 0
    private var totalClicks = 0


    fun click(){
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
}