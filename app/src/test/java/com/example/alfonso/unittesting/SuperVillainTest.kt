package com.example.alfonso.unittesting

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SuperVillainTest {

    lateinit var superVillain: SuperVillain

    @Before
    fun setUp() {
        superVillain = createSuperVillain()
    }

    @After
    fun tearDown() {
    }

    //Test de retorno
    @Test
    fun testFullNameIsNameSpaceLastName() {
        assertEquals(TestData.FULL_NAME, superVillain.fullName)
    }

    //Test de estado
    @Test
    fun testFullNameSetsNameAndLastName() {
        superVillain.fullName = TestData.FULL_NAME

        assertEquals(TestData.MAIN_FIRST_NAME, superVillain.firstName)
        assertEquals(TestData.MAIN_LAST_NAME, superVillain.lastName)

    }

    //Test de comportamiento
    @Test
    fun testAttackFiresWeapon() {
        val fakeWeapon = WeaponDouble()

        superVillain.fire(weapon = fakeWeapon)

        assertTrue(fakeWeapon.wasFired)
    }

    @Test
    fun testSuperVillainKillsSideKickNotAccept() {
        val sideKickStub = SideKickDouble()
        sideKickStub.agreeResponse = false
        superVillain.sideKick = sideKickStub

        superVillain.tellPlans()

        assertNull(superVillain.sideKick)
    }

    @Test
    fun testSuperVillainSideKickAccept() {
        val sideKickStub = SideKickDouble()
        sideKickStub.agreeResponse = true
        superVillain.sideKick = sideKickStub

        superVillain.tellPlans()

        assertNotNull(superVillain.sideKick)
    }

    @Test
    fun testAttackFiresFirstWeapon() {
        val weapon1 = WeaponDouble()
        val weapon2 = WeaponDouble()

        val listWeapon = arrayOf<MegaWeapon>(weapon1, weapon2)
        superVillain.attackFiresWeapons(listWeapons = listWeapon)

        assertTrue(weapon1.wasFired)
    }

    @Test
    fun testStartDominationWorldPlans() {
        val sideKick = SideKickDouble()
        sideKick.weakTargetList = arrayOf("Madrid", "Albacete", "Guadalajara")
        superVillain.sideKick = sideKick

        superVillain.startDominationWorldPlans()

        assertEquals("Madrid", sideKick.firstWeakTarget)
    }

    @Test
    fun testWorldDominationPhaseTwo() {
        val minion = MinionDouble()
        superVillain.worldDominationPhaseTwo(minion = minion)

        assertTrue(minion.verify())
    }

    /**
     * Fake SideKick class.
     */
    class SideKickDouble : SideKick(gadget = GadgetDummy()) {
        var agreeResponse: Boolean = true
        var weakTargetList: Array<String> = arrayOf()
        var firstWeakTarget: String? = null

        override fun agree(): Boolean {
            return agreeResponse
        }

        override fun askForWeakTargets(): Array<String> {
            return weakTargetList
        }

        override fun buildHeadQuarters(city: String) {
            firstWeakTarget = city
        }
    }

    /**
     * Fake gadget class.
     */
    class GadgetDummy : Gadget {
        override fun use() {

        }
    }

    /**
     * Fake weapon class.
     */
    class WeaponDouble : MegaWeapon {

        var wasFired: Boolean = false

        override fun fire() {
            wasFired = true
        }
    }

    /**
     * Fake Minion class.
     */
    class MinionDouble : Minion {

        var hardStuffDone: Boolean = false
        var fightEnemiesDone: Boolean = false

        override fun doHardStuff() {
            hardStuffDone = true
        }

        override fun fightEnemies() {
            fightEnemiesDone = true
        }

        fun verify(): Boolean {
            return hardStuffDone && fightEnemiesDone
        }

    }

}
