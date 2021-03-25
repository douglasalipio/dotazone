package com.br.douglasalipio.cache

import com.couchbase.lite.DatabaseConfiguration

//
object DatabaseManager {
	val database = DatabaseConfiguration()
	private const val PATH = "dotazoneDatabase"

	init {
		database.directory = PATH
	}
}
