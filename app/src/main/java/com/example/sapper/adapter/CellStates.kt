package com.example.sapper.adapter

enum class CellStates(val value: Int) {
    EMPTY_UNPRESSED(-1),
    BOMB_UNPRESSED(10),
    BOMB_PRESSED(11),
    EMPTY_UNPRESSED_AFTER_BOMB_CLICK(12)
}