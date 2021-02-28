package com.br.dotazone.domain


interface UseCaseWithParam<out T, in P> {

	suspend fun execute(param: P): T
}
