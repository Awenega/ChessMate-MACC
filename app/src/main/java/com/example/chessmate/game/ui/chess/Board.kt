package com.example.chessmate.game.ui.chess

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chessmate.game.model.board.Position
import com.example.chessmate.game.model.board.Position.b1
import com.example.chessmate.game.model.board.Position.b5
import com.example.chessmate.game.model.board.Position.b8
import com.example.chessmate.game.model.board.Position.c3
import com.example.chessmate.game.model.board.Position.c6
import com.example.chessmate.game.model.board.Position.c8
import com.example.chessmate.game.model.board.Position.d1
import com.example.chessmate.game.model.board.Position.d5
import com.example.chessmate.game.model.board.Position.d7
import com.example.chessmate.game.model.board.Position.d8
import com.example.chessmate.game.model.board.Position.e2
import com.example.chessmate.game.model.board.Position.e4
import com.example.chessmate.game.model.board.Position.e5
import com.example.chessmate.game.model.board.Position.e7
import com.example.chessmate.game.model.board.Position.f1
import com.example.chessmate.game.model.board.Position.f3
import com.example.chessmate.game.model.board.Position.g4
import com.example.chessmate.game.model.game.controller.GameController
import com.example.chessmate.game.model.game.state.GamePlayState
import com.example.chessmate.game.model.game.state.GameSnapshotState
import com.example.chessmate.game.model.game.state.UiState
import com.example.chessmate.game.ui.chess.board.BoardRenderProperties
import com.example.chessmate.game.ui.chess.board.DefaultBoardRenderer
import com.example.chessmate.ui.theme.ChessMateTheme

@Composable
fun Board(
    gamePlayState: GamePlayState,
    gameController: GameController,
    isFlipped: Boolean = false,
) {
    Board(
        fromState = gamePlayState.gameState.lastActiveState,
        toState = gamePlayState.gameState.currentSnapshotState,
        uiState = gamePlayState.uiState,
        isFlipped = isFlipped,
        onClick = { position -> gameController.onClick(position) }
    )
}

@Composable
fun Board(
    fromState: GameSnapshotState,
    toState: GameSnapshotState,
    uiState: UiState,
    isFlipped: Boolean = false,
    onClick: (Position) -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        val boardProperties =
            BoardRenderProperties(
                fromState = fromState,
                toState = toState,
                uiState = uiState,
                squareSize = maxWidth / 8,
                isFlipped = isFlipped,
                onClick = onClick
            )

        DefaultBoardRenderer.decorations.forEach {
            it.Render(properties = boardProperties)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardPreview() {
    ChessMateTheme {
        var gamePlayState = GamePlayState()
        val gameController = GameController({ gamePlayState }, { gamePlayState = it }).apply {
            applyMove(e2, e4)
            applyMove(e7, e5)
            applyMove(b1, c3)
            applyMove(b8, c6)
            applyMove(f1, b5)
            applyMove(d7, d5)
            applyMove(e4, d5)
            applyMove(d8, d5)
            applyMove(d1, f3)
            applyMove(c8, g4)
            onClick(f3)
        }

        Board(
            gameController = gameController,
            gamePlayState = gamePlayState
        )
    }
}
