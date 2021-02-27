package com.br.dotazone.domain


interface Mapper<in FROM, out TO> {

	fun map(from: FROM): TO
}
