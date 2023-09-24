package jp.co.yumemi.android.code_check

import jp.co.yumemi.android.code_check.screen.detail.RepositoryDetailViewModel
import jp.co.yumemi.android.code_check.usecase.GetWatchersCountUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModules: Module get() = module {
    includes(repositoryDetailModules)
}

private val repositoryDetailModules = module {
    factory { GetWatchersCountUseCase() }
    viewModel { RepositoryDetailViewModel( get() ) }
}