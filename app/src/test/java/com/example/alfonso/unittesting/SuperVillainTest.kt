package com.example.alfonso.unittesting

import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

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
        val fakeWeapon = mock(MegaWeapon::class.java)//WeaponDouble()

        superVillain.fire(weapon = fakeWeapon)

        verify(fakeWeapon).fire()
    }

    @Test
    fun testSuperVillainKillsSideKickNotAccept() {
        val sideKickStub = mock(SideKick::class.java)
        whenever(sideKickStub.agree()).thenReturn(false)

        superVillain.sideKick = sideKickStub

        superVillain.tellPlans()

        assertNull(superVillain.sideKick)
    }

    @Test
    fun testSuperVillainSideKickAccept() {
        val sideKickStub = mock(SideKick::class.java)
        whenever(sideKickStub.agree()).thenReturn(true)

        superVillain.sideKick = sideKickStub

        superVillain.tellPlans()

        assertNotNull(superVillain.sideKick)
    }

    @Test
    fun testAttackFiresFirstWeapon() {
        val weapon1 = mock(MegaWeapon::class.java)
        val weapon2 = mock(MegaWeapon::class.java)

        val listWeapon = arrayOf<MegaWeapon>(weapon1, weapon2)
        superVillain.attackFiresWeapons(listWeapons = listWeapon)

        verify(weapon1, atLeastOnce()).fire()
        verify(weapon2, never()).fire()
    }

    @Test
    fun testStartDominationWorldPlans() {
        val sideKick = mock(SideKick::class.java)
        whenever(sideKick.askForWeakTargets()).thenReturn(arrayOf("Madrid", "Albacete", "Guadalajara"))

        superVillain.sideKick = sideKick

        superVillain.startDominationWorldPlans()

        verify(sideKick).buildHeadQuarters(city = "Madrid")
    }

    @Test
    fun testWorldDominationPhaseTwo() {
        val minion = mock(Minion::class.java)
        superVillain.worldDominationPhaseTwo(minion = minion)

        verify(minion, times(1)).fightEnemies()
        verify(minion, times(1)).doHardStuff()
    }

    @Test
    fun testSecretsAreCyphered() {
        val cypher = mock(Cypher::class.java)
        whenever(cypher.encrypt(any(), any())).thenAnswer {
            val message = it.arguments[0] as String
            "O2O" + message + "O2O"
        }

        val sideKick = mock(SideKick::class.java)

        superVillain.sideKick = sideKick
        superVillain.tellSecrets(cypher, TestData.MESSAGE)

        argumentCaptor<String>().apply {
            verify(sideKick).listenSecrets(capture())
            assertEquals(lastValue, TestData.CYPHER_MESSAGE)
        }

    }

    /**
     * Fake SideKick class.
     */
    /*class SideKickDouble : SideKick(gadget = GadgetDummy()) {
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
    }*/

    /**
     * Fake gadget class.
     */
    /*class GadgetDummy : Gadget {
        override fun use() {

        }
    }*/

    /**
     * Fake weapon class.
     */
    /*class WeaponDouble : MegaWeapon {

        var wasFired: Boolean = false

        override fun fire() {
            wasFired = true
        }
    }*/

    /**
     * Fake Minion class.
     */
    /*class MinionDouble : Minion {

        var hardStuffDone: Boolean = false
        var fightEnemiesDone: Boolean = false

        override fun doHardStuff() {
            hardStuffDone = true
        }

        override fun fightEnemies() {
            fightEnemiesDone = true
        }

        fun verify() {
            assertTrue(hardStuffDone)
            assertTrue(fightEnemiesDone)
        }
    }*/

    /**
     * Fake Cypher class.
     */
    class FakeCypher : Cypher {

        override fun encrypt(message: String, key: String): String {
            return "O2O$message"
        }

    }

}
