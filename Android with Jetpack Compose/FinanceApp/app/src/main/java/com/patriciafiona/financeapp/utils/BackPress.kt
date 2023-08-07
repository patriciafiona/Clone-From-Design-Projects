package com.patriciafiona.financeapp.utils

sealed class BackPress {
    object Idle : BackPress()
    object InitialTouch : BackPress()
}