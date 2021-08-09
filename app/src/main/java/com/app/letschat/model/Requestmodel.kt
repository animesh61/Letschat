package com.app.letschat.model

data class SigninRequest(  var source:String?=null,
                           var device_token:String?=null,
                            var device_type:String?=null,
                            var email:String?=null,
                            var password:String?=null)

data class ForgotpasswordRequest(var source:String?=null,
                                 var email:String?=null  )