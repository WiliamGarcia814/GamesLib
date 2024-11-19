package com.whgarcia.gameslib.core.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.whgarcia.gameslib.core.presentation.util.ObserveAsEvent
import com.whgarcia.gameslib.core.presentation.util.toString
import com.whgarcia.gameslib.game.presentation.game_detail.GameDetailScreen
import com.whgarcia.gameslib.game.presentation.game_list.GameListAction
import com.whgarcia.gameslib.game.presentation.game_list.GameListEvent
import com.whgarcia.gameslib.game.presentation.game_list.GameListScreen
import com.whgarcia.gameslib.game.presentation.game_list.GameListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveGameListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: GameListViewModel = koinViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvent(events = viewModel.events) { event ->
        when(event) {
            is GameListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                GameListScreen(
                    state = state,
                    onAction = { action ->
                        viewModel.onAction(action)
                        when(action) {
                            is GameListAction.OnGameClick -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }
                            is GameListAction.SearchGames -> {}
                            is GameListAction.LoadNextPage -> {}
                            is GameListAction.clear -> {}
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                GameDetailScreen(
                    state = state,
                    onAction = { action ->
                        viewModel.onAction(action)
                    }
                )
            }
        },
        modifier = modifier
    )
}