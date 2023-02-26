package com.plugin.showcaserestfullapi.service

import com.plugin.showcaserestfullapi.model.RegisterUserRequest
import com.plugin.showcaserestfullapi.model.UserResponse
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService {
    fun register(registerUserRequest: RegisterUserRequest) : UserResponse
}