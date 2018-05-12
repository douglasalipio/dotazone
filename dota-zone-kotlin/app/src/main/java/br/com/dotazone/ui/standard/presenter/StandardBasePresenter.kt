package br.com.dotazone.ui.standard.presenter

import br.com.dotazone.data.local.standard.Standard
import br.com.dotazone.ui.base.presenter.BasePresenter
import br.com.dotazone.ui.standard.interactor.StandardBaseInteractor
import br.com.dotazone.ui.standard.view.StandardBaseView

interface StandardBasePresenter<V : StandardBaseView, I : StandardBaseInteractor> : BasePresenter<V, I> {
    fun onViewPrepared()

    fun onValidateInsertStandard(standard: Standard)

    fun getData()
}

