package com.example.alfonso.unittesting

open class SideKick(var gadget: Gadget) {

    open fun agree(): Boolean {
        return true
    }

    open fun askForWeakTargets(): Array<String> {
        return arrayOf()
    }

    open fun buildHeadQuarters(city: String) {
        //Empty
    }

}