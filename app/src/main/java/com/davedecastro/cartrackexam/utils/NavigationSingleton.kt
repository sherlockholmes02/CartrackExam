package com.davedecastro.cartrackexam.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object NavigationSingleton {

    fun navigate(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String? = null,
        enterAnimation: Int = 0,
        exitAnimation: Int = 0
    ) {
        fragmentManager
            .beginTransaction()
            .also {
                if (enterAnimation != 0 && exitAnimation != 0)
                    it.setCustomAnimations(enterAnimation, exitAnimation)
            }
            .replace(containerId, fragment, tag)
            .commit()
    }
}