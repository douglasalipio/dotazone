package br.com.dotazone.ui.standard.interactor


import br.com.dotazone.data.local.standard.Standard
import br.com.dotazone.data.local.standard.StandardRepo
import br.com.dotazone.data.network.ApiHelper
import br.com.dotazone.ui.base.interactor.BaseInteractorImp
import javax.inject.Inject

class StandardInteractorImp @Inject constructor(
        private val standardRepo: StandardRepo,
        apiHelper: ApiHelper) : BaseInteractorImp(apiHelper), StandardBaseInteractor {


    override fun submitQuestion(standard: Standard) = standardRepo.insertStandard(standard)

    override fun getBlogList() = apiHelper.getBlogApiCall()

    override fun getData() = standardRepo.getStandard()
}
