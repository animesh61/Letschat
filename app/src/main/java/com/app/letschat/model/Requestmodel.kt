package com.app.letschat.model

data class SigninRequest(  var source:String?=null,
                           var device_token:String?=null,
                            var device_type:String?=null,
                            var email:String?=null,
                            var password:String?=null)

data class ForgotpasswordRequest(var source:String?=null,
                                 var email:String?=null  )


data class ChangepasswordRequest(var source:String?=null,
                                 var email:String?=null,
                                 var password:String?=null,
                                  var reset_password_otp:String?=null)



data class SignupRequest(var source:String?=null,
                                 var first_name:String?=null,
                                 var last_name:String?=null,
                                 var email:String?=null,
                                 var password:String?=null )

data class verifyregisterRequest(var email:String?=null,
                                  var otp:String?=null)


data class profileRequest(var source:String?=null,
                                 var user_id:String?=null)