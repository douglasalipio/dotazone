package br.com.dotazone.ui.standard.view

import br.com.dotazone.data.local.standard.Standard
import br.com.dotazone.data.network.Blog
import br.com.dotazone.ui.base.view.BaseView

interface StandardBaseView : BaseView {

    fun displayBlogList(blogs: List<Blog>?)

    fun displayData(standard: List<Standard>)
}
