package br.com.dotazone.ui.base.presenter

import br.com.dotazone.ui.base.interactor.BaseInteractor
import br.com.dotazone.ui.base.view.BaseView

interface BasePresenter<V : BaseView, I : BaseInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}
