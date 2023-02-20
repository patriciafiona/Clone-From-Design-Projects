package com.patriciafiona.marioworld.utils

sealed class BackPress {
    object Idle : BackPress()
    object InitialTouch : BackPress()
}