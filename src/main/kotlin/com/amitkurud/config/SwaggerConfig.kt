// package com.amitkurud.config
//
// import io.swagger.annotations.ApiModelProperty
// import org.springframework.context.annotation.Bean
// import org.springframework.context.annotation.Configuration
// import org.springframework.data.domain.Pageable
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
// import springfox.documentation.builders.PathSelectors
// import springfox.documentation.builders.RequestHandlerSelectors
// import springfox.documentation.spi.DocumentationType
// import springfox.documentation.spring.web.plugins.Docket
// import springfox.documentation.swagger2.annotations.EnableSwagger2
//
//
// @Configuration
// @EnableSwagger2
// class SwaggerConfig : WebMvcConfigurer {
// @Bean
// fun api(): Docket {
// return Docket(DocumentationType.SWAGGER_2)
// .select()
// .apis(RequestHandlerSelectors.any())
// .paths(PathSelectors.any())
// .build()
// .directModelSubstitute(Pageable::class.java, SwaggerPageable::class.java)
//
// }
//
// override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
// registry.addResourceHandler("swagger-ui.html")
// .addResourceLocations("classpath:/META-INF/resources/")
// registry.addResourceHandler("/webjars/**")
// .addResourceLocations("classpath:/META-INF/resources/webjars/")
// }
//
// data class SwaggerPageable(
// @ApiModelProperty("Number of records per page", example = "20")
// val size: Int?,
// @ApiModelProperty("Results page you want to retrieve (0..N)", example = "0")
// val page: Int?,
// @ApiModelProperty(
// "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.",
// example = "&sort=created,asc"
// )
// var sort: String?
// )
// }*