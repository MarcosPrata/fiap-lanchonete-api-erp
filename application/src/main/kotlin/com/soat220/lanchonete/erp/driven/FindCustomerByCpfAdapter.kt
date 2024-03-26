package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.erp.port.FindCustomerByCpfPort
import org.springframework.stereotype.Service

@Service
class FindCustomerByCpfAdapter (
    private val customerRepository: CustomerRepository
): FindCustomerByCpfPort {

    override fun execute(cpf: String): Result<Customer, DomainException> {
        return try {
            Success(customerRepository.findByCpf(cpf).toDomain())
        } catch (e: Exception) {
            Failure(DomainException(e, ErrorCode.ENTITY_NOT_FOUND_ERROR))
        }
    }
}