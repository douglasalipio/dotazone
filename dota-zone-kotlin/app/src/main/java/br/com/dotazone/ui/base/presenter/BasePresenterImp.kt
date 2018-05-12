package br.com.dotazone.ui.base.presenter

import br.com.dotazone.ui.base.interactor.BaseInteractor
import br.com.dotazone.ui.base.view.BaseView
import br.com.dotazone.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


abstract class BasePresenterImp<V : BaseView, I : BaseInteractor>
internal constructor(protected var interactor: I?,
                     protected val schedulerProvider: SchedulerProvider,
                     protected val compositeDisposable: CompositeDisposable) : BasePresenter<V, I> {

    private var view: V? = null

    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

}
