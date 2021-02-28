package com.br.dotazone.domain


interface UseCaseWithoutParam<out T> {

	suspend fun execute(): T
}
