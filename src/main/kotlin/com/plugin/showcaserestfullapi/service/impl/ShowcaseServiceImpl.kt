package com.plugin.showcaserestfullapi.service.impl

import com.cloudinary.utils.ObjectUtils
import com.plugin.showcaserestfullapi.config.CloudinaryConfig
import com.plugin.showcaserestfullapi.entity.Showcase
import com.plugin.showcaserestfullapi.helper.NotFoundException
import com.plugin.showcaserestfullapi.helper.ValidationUtil
import com.plugin.showcaserestfullapi.model.CreateShowcaseRequest
import com.plugin.showcaserestfullapi.model.ListShowcaseRequest
import com.plugin.showcaserestfullapi.model.ShowcaseResponse
import com.plugin.showcaserestfullapi.repository.ShowcaseRepository
import com.plugin.showcaserestfullapi.service.ShowcaseService
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ShowcaseServiceImpl(val showcaseRepository: ShowcaseRepository, val validationUtil: ValidationUtil) :
    ShowcaseService {
    override fun create(createShowcaseRequest: CreateShowcaseRequest): ShowcaseResponse {
//        validationUtil.validate(createShowcaseRequest)
        val cloudinary = CloudinaryConfig()
        val uploadResponse = cloudinary.cloudinaryAccount().uploader().upload(createShowcaseRequest.image.bytes, ObjectUtils.asMap())

        val showcase = Showcase(
            title = createShowcaseRequest.title!!,
            image = uploadResponse.get("url").toString(),
            description = createShowcaseRequest.description!!,
            categoryId = createShowcaseRequest.categoryId!!,
            createdAt = Date(),
            updatedAt = Date()
        )
        showcaseRepository.save(showcase)
        return convertShowcaseToShowcaseResponse(showcase)

    }

    override fun get(id: String): ShowcaseResponse {
        val showcase = showcaseRepository.findByIdOrNull(id)
        if (showcase === null) {
            throw NotFoundException()
        } else {
            return convertShowcaseToShowcaseResponse(showcase)
        }
    }

    override fun list(listShowcaseRequest: ListShowcaseRequest): List<ShowcaseResponse> {
        val page = showcaseRepository.findAll(
            PageRequest.of(
                listShowcaseRequest.page,
                listShowcaseRequest.size
            )
        )
        val showcases: List<Showcase> = page.get().collect(Collectors.toList())
        return showcases.map { convertShowcaseToShowcaseResponse(it) }
    }

    private fun convertShowcaseToShowcaseResponse(showcase: Showcase): ShowcaseResponse {
        return ShowcaseResponse(
            id = showcase.id,
            title = showcase.title,
            image = showcase.image,
            description = showcase.description,
            categoryId = showcase.categoryId,
            createdAt = showcase.createdAt,
            updatedAt = showcase.updatedAt
        )
    }
}