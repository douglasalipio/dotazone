package br.com.dotazone.ui.standard.interactor

import br.com.dotazone.data.local.standard.Standard
import br.com.dotazone.data.network.BlogResponse
import br.com.dotazone.ui.base.interactor.BaseInteractor
import io.reactivex.Observable

interface StandardBaseInteractor : BaseInteractor {

    fun getBlogList(): Observable<BlogResponse>

    fun submitQuestion(standard: Standard): Long

    fun getData(): List<Standard>
}
