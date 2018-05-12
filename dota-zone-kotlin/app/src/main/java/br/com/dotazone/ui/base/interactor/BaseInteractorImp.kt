package br.com.dotazone.ui.base.interactor

import br.com.dotazone.data.network.ApiHelper

open class BaseInteractorImp() : BaseInteractor {

    protected lateinit var apiHelper: ApiHelper

    constructor(apiHelper: ApiHelper) : this() {
        this.apiHelper = apiHelper
    }

    override fun isUserLoggedIn() = true
}
