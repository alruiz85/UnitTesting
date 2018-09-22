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
        val sideKickStub = SideKickStub()
        sideKickStub.agreeResponse = false
        superVillain.sideKick = sideKickStub

        superVillain.tellPlans()

        assertNull(superVillain.sideKick)
    }

    @Test
    fun testSuperVillainSideKickAccept() {
        val sideKickStub = SideKickStub()
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

    /**
     * Fake SideKick class.
     */
    class SideKickStub : SideKick(gadget = GadgetDummy()) {
        var agreeResponse: Boolean = true

        override fun agree(): Boolean {
            return agreeResponse
        }
    }

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

}