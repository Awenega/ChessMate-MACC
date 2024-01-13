package com.example.chessmate.game.ui.app

import androidx.compose.runtime.Composable
import com.example.chessmate.game.model.game.preset.Preset
import com.example.chessmate.game.model.game.state.GamePlayState

@Composable
fun Preset(preset: Preset) {
    Game(
        state = GamePlayState(),
        preset = preset
    )
}
