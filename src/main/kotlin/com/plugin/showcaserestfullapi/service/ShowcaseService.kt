package com.plugin.showcaserestfullapi.service

import com.plugin.showcaserestfullapi.model.CreateShowcaseRequest
import com.plugin.showcaserestfullapi.model.ListShowcaseRequest
import com.plugin.showcaserestfullapi.model.ShowcaseResponse

interface ShowcaseService {
    fun create(createShowcaseRequest: CreateShowcaseRequest) : ShowcaseResponse
    fun get(id : String) : ShowcaseResponse
    fun list(listShowcaseRequest: ListShowcaseRequest) : List<ShowcaseResponse>
}