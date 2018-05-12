package br.com.dotazone.ui.standard.presenter

import br.com.dotazone.data.local.standard.Standard
import br.com.dotazone.ui.base.presenter.BasePresenterImp
import br.com.dotazone.ui.standard.interactor.StandardBaseInteractor
import br.com.dotazone.ui.standard.view.StandardBaseView
import br.com.dotazone.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class StandardPresenterImp<V : StandardBaseView, I : StandardBaseInteractor>
@Inject internal constructor(
        interactor: I,
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable) : BasePresenterImp<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable), StandardBasePresenter<V, I> {


    override fun onValidateInsertStandard(standard: Standard) {
        interactor.let {
            it!!.submitQuestion(standard)
        }
    }

    override fun getData() {
        interactor?.let {
            getView()?.displayData(it.getData())
        }
    }

    override fun onViewPrepared() {
        getView()?.showProgress()
        interactor?.let {
            it.getBlogList()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe { blogResponse ->
                        getView()?.let {
                            it.hideProgress()
                            it.displayBlogList(blogResponse.data)
                        }
                    }
        }
    }


}
