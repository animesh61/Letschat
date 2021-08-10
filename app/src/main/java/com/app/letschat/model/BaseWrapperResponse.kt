package com.app.letschat.model

import com.google.gson.JsonObject

class BaseWrapperResponse {
    var status: Status? = null
    var result:Int=0

}

class Status {
    var error_code: Int = 0
    var message: String? = null
}

