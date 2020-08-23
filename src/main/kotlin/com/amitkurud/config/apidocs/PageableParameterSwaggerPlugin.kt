/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config.apidocs

import com.fasterxml.classmate.ResolvedType
import com.fasterxml.classmate.TypeResolver
import com.google.common.collect.Lists
import org.springframework.data.domain.Pageable
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.schema.ModelReference
import springfox.documentation.schema.ResolvedTypes
import springfox.documentation.schema.TypeNameExtractor
import springfox.documentation.service.Parameter
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.schema.contexts.ModelContext
import springfox.documentation.spi.service.OperationBuilderPlugin
import springfox.documentation.spi.service.contexts.OperationContext
import springfox.documentation.spi.service.contexts.ParameterContext


/**
 * The Springfox Plugin to resolve [org.springframework.data.domain.Pageable] parameter into plain fields.
 */
class PageableParameterSwaggerPlugin(val nameExtractor: TypeNameExtractor, val resolver: TypeResolver) :
    OperationBuilderPlugin {
    private val pageableType: ResolvedType = resolver.resolve(Pageable::class.java)

    /** {@inheritDoc}  */
    override fun supports(delimiter: DocumentationType): Boolean {
        return DocumentationType.SWAGGER_2 == delimiter
    }

    /** {@inheritDoc}  */
    override fun apply(context: OperationContext) {
        val parameters: MutableList<Parameter> = Lists.newArrayList()
        for (methodParameter in context.parameters) {
            val resolvedType = methodParameter.parameterType
            if (pageableType == resolvedType) {
                val parameterContext = ParameterContext(
                    methodParameter,
                    ParameterBuilder(),
                    context.documentationContext,
                    context.genericsNamingStrategy,
                    context
                )
                parameters.add(createPageParameter(parameterContext))
                parameters.add(createSizeParameter(parameterContext))
                parameters.add(createSortParameter(parameterContext))
                context.operationBuilder().parameters(parameters)
            }
        }
    }

    protected fun createPageParameter(context: ParameterContext): Parameter {
        val intModel = createModelRefFactory(context)?.apply(resolver.resolve(Integer.TYPE))
        return ParameterBuilder()
            .name(pageName)
            .parameterType(PAGE_TYPE)
            .modelRef(intModel)
            .description(PAGE_DESCRIPTION)
            .build()
    }


    protected fun createSizeParameter(context: ParameterContext): Parameter {
        val intModel = createModelRefFactory(context)?.apply(resolver.resolve(Integer.TYPE))
        return ParameterBuilder()
            .name(sizeName)
            .parameterType(SIZE_TYPE)
            .modelRef(intModel)
            .description(SIZE_DESCRIPTION)
            .build()
    }

    protected fun createSortParameter(context: ParameterContext): Parameter {
        val stringModel =
            createModelRefFactory(context)?.apply(resolver.resolve(MutableList::class.java, String::class.java))
        return ParameterBuilder()
            .name(sortName)
            .parameterType(SORT_TYPE)
            .modelRef(stringModel)
            .allowMultiple(true)
            .description(SORT_DESCRIPTION)
            .build()
    }

    protected fun createModelRefFactory(context: ParameterContext): com.google.common.base.Function<ResolvedType, ModelReference>? {
        val modelContext = ModelContext.inputParam(
            context.groupName,
            context.resolvedMethodParameter().parameterType,
            context.documentationType,
            context.alternateTypeProvider,
            context.genericNamingStrategy,
            context.ignorableParameterTypes
        )
        return ResolvedTypes.modelRefFactory(modelContext, nameExtractor)
    }

    companion object {
        /**
         * Page name may be varied.
         * See [org.springframework.data.web.PageableHandlerMethodArgumentResolver.setPageParameterName]
         *
         * @return The page parameter name
         */
        /** Constant `DEFAULT_PAGE_NAME="page"`  */
        protected const val pageName = "page"

        /** Constant `PAGE_TYPE="query"`  */
        const val PAGE_TYPE = "query"

        /** Constant `PAGE_DESCRIPTION="Page number of the requested page"`  */
        const val PAGE_DESCRIPTION = "Page number of the requested page"

        /**
         * Size name may be varied.
         * See [org.springframework.data.web.PageableHandlerMethodArgumentResolver.setSizeParameterName]
         *
         * @return The size parameter name
         */
        /** Constant `DEFAULT_SIZE_NAME="size"`  */
        protected const val sizeName = "size"

        /** Constant `SIZE_TYPE="query"`  */
        const val SIZE_TYPE = "query"

        /** Constant `SIZE_DESCRIPTION="Size of a page"`  */
        const val SIZE_DESCRIPTION = "Size of a page"

        /**
         * Sort name may be varied.
         * See [org.springframework.data.web.SortHandlerMethodArgumentResolver.setSortParameter]
         *
         * @return The sort parameter name
         */
        /** Constant `DEFAULT_SORT_NAME="sort"`  */
        protected const val sortName = "sort"

        /** Constant `SORT_TYPE="query"`  */
        const val SORT_TYPE = "query"

        const val SORT_DESCRIPTION = ("Sorting criteria in the format: property(,asc|desc). "
                + "Default sort order is ascending. "
                + "Multiple sort criteria are supported.")
    }

}