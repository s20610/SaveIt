package com.borysante.saveit.data.result

sealed class ApiResult<out R, out E> {
    data class Success<out R>(val data: R) : ApiResult<R, Nothing>()
    data class Error<out E>(val error: E) : ApiResult<Nothing, E>()

    companion object {
        fun <R, E> ApiResult<R, E>.fold(onSuccess: (R) -> Unit, onError: (E) -> Unit) {
            when (this) {
                is Success -> onSuccess(data)
                is Error -> onError(error)
            }
        }
    }
}