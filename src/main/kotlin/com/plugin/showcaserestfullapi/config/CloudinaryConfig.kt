package com.plugin.showcaserestfullapi.config

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CloudinaryConfig {
    @Bean
    fun cloudinaryAccount() : Cloudinary{
        return Cloudinary(ObjectUtils.asMap(
            "cloud_name", "kmalifdev",
            "api_key", "812952289488894",
            "api_secret", "2BoMB5qbjwpnWACUtEATXoTmYao"
        ))
    }
}