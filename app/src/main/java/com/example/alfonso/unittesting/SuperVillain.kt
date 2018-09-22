package com.example.alfonso.unittesting

data class SuperVillain(var firstName: String,
                        var lastName: String,
                        var sideKick: SideKick? = null) {

    fun fire(weapon: MegaWeapon) {
        weapon.fire()
    }

    var fullName: String
        get() = "$firstName $lastName"
        set(value) {
            val list = value.split(" ")
            firstName = list[0]
            lastName = list[1]
        }

    fun tellPlans() {
        sideKick?.let {
            if (!it.agree()) sideKick = null
        }
    }

    fun attackFiresWeapons(listWeapons: Array<MegaWeapon>) {
        listWeapons[0].fire()
    }

}
