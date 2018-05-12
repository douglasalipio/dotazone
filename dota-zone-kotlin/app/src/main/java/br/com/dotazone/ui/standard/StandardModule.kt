package org.secfirst.dotazone.ui.standard

import br.com.dotazone.ui.standard.interactor.StandardBaseInteractor
import br.com.dotazone.ui.standard.interactor.StandardInteractorImp
import br.com.dotazone.ui.standard.presenter.StandardBasePresenter
import br.com.dotazone.ui.standard.presenter.StandardPresenterImp
import br.com.dotazone.ui.standard.view.StandardBaseView
import dagger.Module
import dagger.Provides

@Module
class StandardModule {

    @Provides
    internal fun provideBlogInteractor(interactor: StandardInteractorImp): StandardBaseInteractor = interactor

    @Provides
    internal fun provideBlogPresenter(presenter: StandardPresenterImp<StandardBaseView, StandardBaseInteractor>)
            : StandardBasePresenter<StandardBaseView, StandardBaseInteractor> = presenter

}


