package br.com.dotazone.ui.standard.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.dotazone.R
import br.com.dotazone.data.local.standard.Standard
import br.com.dotazone.data.network.Blog
import br.com.dotazone.ui.base.view.BaseFragment
import br.com.dotazone.ui.standard.interactor.StandardBaseInteractor
import br.com.dotazone.ui.standard.presenter.StandardBasePresenter
import javax.inject.Inject




class StandardFragment : BaseFragment(), StandardBaseView {


    @Inject
    internal lateinit var presenter: StandardBasePresenter<StandardBaseView, StandardBaseInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_standard, container, false)


    companion object {
        fun newInstance(): StandardFragment = StandardFragment()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        presenter.onViewPrepared()
    }

    override fun displayBlogList(blogs: List<Blog>?) {

        //Network's test
        for (blog in blogs!!) Log.e("test", "----"+ blog.title)

        //Insert db
        presenter.onValidateInsertStandard(Standard(System.currentTimeMillis(), "douglas"))


        Handler().postDelayed({ presenter.getData() }, 0)
    }

    override fun displayData(standard: List<Standard>) {
        //List data from network
        if(!standard.isEmpty()) Log.i("test", "saved - " + standard[1].questionText)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}
