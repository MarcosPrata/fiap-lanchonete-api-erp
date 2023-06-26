package com.soat220.lanchonete.customer.adapter

import com.soat220.lanchonete.customer.adapter.postgresdb.CustomerRepository
import com.soat220.lanchonete.customer.model.Customer
import com.soat220.lanchonete.customer.port.FindCustomerByCpfPort
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import org.springframework.stereotype.Service

@Service
class FindCustomerByCpfAdapter (
    private val customerRepository: CustomerRepository
): FindCustomerByCpfPort {

    override fun execute(cpf: String): Result<Customer, DomainException> {
        return try {
            Success(customerRepository.findByCpf(cpf).toDomain())
        } catch (e: Exception) {
            return Failure(
                DomainException(e, ErrorCode.DATABASE_ERROR)
            )
        }

    }
}