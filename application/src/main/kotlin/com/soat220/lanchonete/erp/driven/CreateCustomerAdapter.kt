package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.erp.port.CreateCustomerPort
import com.soat220.lanchonete.erp.port.FindCustomerByCpfPort
import org.springframework.stereotype.Service

@Service
class CreateCustomerAdapter(
    private val findCustomerByCpfPort: FindCustomerByCpfPort,
    private val customerRepository: CustomerRepository
): CreateCustomerPort {

    override fun execute(customer: Customer): Result<Customer, DomainException> {
        val customerEntity = com.soat220.lanchonete.common.driven.postgresdb.model.Customer.fromDomain(customer)

        val customerAux = findCustomerByCpfPort.execute(customer.cpf!!).getOrNull()

        if (customerAux != null) {
            customerEntity.email = customerAux.email
            customerEntity.name = customerAux.name
        }

        return Success(customerRepository.save(customerEntity).toDomain())
    }
}