package com.whgarcia.gameslib.di

import com.whgarcia.gameslib.core.data.networking.HttpClientFactory
import com.whgarcia.gameslib.game.data.networking.RemoteGameDataSource
import com.whgarcia.gameslib.game.domain.GameDataSource
import com.whgarcia.gameslib.game.presentation.game_list.GameListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteGameDataSource).bind<GameDataSource>()

    viewModelOf(::GameListViewModel)
}