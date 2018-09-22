package com.example.alfonso.unittesting

data class SuperVillain(var firstName: String,
                        var lastName: String,
                        var sideKick: SideKick? = null,
                        var minion: Minion? = null) {

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

    fun startDominationWorldPlans() {
        sideKick?.apply {
            val places = askForWeakTargets()
            buildHeadQuarters(city = places[0])
        }
    }

    fun worldDominationPhaseTwo(minion: Minion) {
        minion.doHardStuff()
        minion.fightEnemies()
    }

    fun tellSecrets(cypher: Cypher, message: String) {
        sideKick?.apply {
            val messageEncrypt = cypher.encrypt(message = "", key = "")
            listenSecrets(messageEncrypt)
        }
    }

}
