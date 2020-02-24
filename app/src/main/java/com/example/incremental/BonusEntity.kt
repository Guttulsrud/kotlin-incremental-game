package com.example.incremental


class BonusEntity (name:String, bonus:Int, price:Int, amountOwned:Int){
    private val name:String
    private val defaultBonusPerSecond:Int
    private var price:Int
    private var amountOwned:Int
//    private var multiplier:Float = 1.0f
//    private val image

    init{
        this.name = name
        this.defaultBonusPerSecond = bonus
        this.price = price
        this.amountOwned = amountOwned
    }

    fun getName():String{
        return this.name
    }
    fun getBonus():Int{
        return this.defaultBonusPerSecond*this.amountOwned
    }
    fun getDefaultBonus():Int{
        return this.defaultBonusPerSecond
    }
    fun getPrice():Int{
        return this.price
    }
    fun getAmoutOwned():Int{
        return this.amountOwned
    }
    fun boughtNew(amount:Int){
        this.amountOwned+=amount
        this.price += price/10
    }
//    fun increaseMultiplier(percentIncrease:Int){
//        this.multiplier = multiplier*(1+(percentIncrease/100))
//    }
}


