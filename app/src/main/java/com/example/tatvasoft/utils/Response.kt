package com.example.tatvasoft.utils

import java.lang.Exception

class Response<T> {
    var responseBody: T? = null
    var exception: Exception? = null

    constructor(responseBody: T?) {
        this.responseBody = responseBody
    }

    constructor(exception: Exception?) {
        this.exception = exception
    }
}