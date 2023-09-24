package jp.co.yumemi.android.code_check

import jp.co.yumemi.android.code_check.screen.detail.RepositoryDetailViewModel
import jp.co.yumemi.android.code_check.usecase.GetWatchersCountUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    factory { GetWatchersCountUseCase() }
    viewModel { RepositoryDetailViewModel( get() ) }
}