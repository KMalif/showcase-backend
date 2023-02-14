package com.plugin.showcaserestfullapi.model

import javax.validation.constraints.NotBlank

data class CreateShowcaseRequest (
    @field:NotBlank
    val id : String?,
    @field:NotBlank
    val title : String?,
    @field:NotBlank
    val image : String?,
    @field:NotBlank
    val description: String?,
    @field:NotBlank
    val categoryId : String?
)