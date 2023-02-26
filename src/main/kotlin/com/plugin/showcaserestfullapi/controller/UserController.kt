package com.plugin.showcaserestfullapi.controller

import com.plugin.showcaserestfullapi.helper.BaseResponse
import com.plugin.showcaserestfullapi.model.RegisterUserRequest
import com.plugin.showcaserestfullapi.model.UserResponse
import com.plugin.showcaserestfullapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class UserController  {
    @get:Autowired
    abstract val userService : UserService

    @PostMapping(value =["/api/register"],
        produces = ["application/json"],
        consumes = ["application/json"])
    fun register(@RequestBody registerUserRequest: RegisterUserRequest) : BaseResponse<UserResponse>{
        val response = userService.register(registerUserRequest)
        return BaseResponse(
            code = 200,
            status = "Register Success",
            data = response
        )
    }
}