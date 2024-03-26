package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.erp.exception.CreateProductException
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.erp.port.CreateProductPort
import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Product
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success

import org.springframework.stereotype.Service
import com.soat220.lanchonete.common.model.Product as DomainProduct

@Service
class CreateProductAdapter(
    private val productRepository: ProductRepository
) : CreateProductPort {
    override fun execute(product: DomainProduct): Result<DomainProduct, DomainException> {

        return try {
            Success(productRepository.save(Product.fromDomain(product)).toDomain())
        } catch (e: Exception) {
            return Failure(
                CreateProductException(
                    product.name,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }
    }
}