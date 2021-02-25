package com.br.dotazone.model.entity

class NavDrawerItem {
	var title: String? = null

	// boolean to set visiblity of the counter
	var counterVisibility = false

	constructor() {}
	constructor(title: String?) {
		this.title = title
	}

	constructor(title: String?, icon: Int, isCounterVisible: Boolean, count: String?) {
		this.title = title
		counterVisibility = isCounterVisible
	}
}

