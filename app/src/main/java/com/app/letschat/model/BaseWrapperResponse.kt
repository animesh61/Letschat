package com.app.letschat.model

import com.google.gson.JsonObject

class BaseWrapperResponse {
    var status: Status? = null
}

class Status {
    var error_code: Int = 0
    var message: String? = null
}
