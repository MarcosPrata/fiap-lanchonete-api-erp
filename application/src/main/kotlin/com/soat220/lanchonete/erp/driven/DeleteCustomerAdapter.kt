package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.erp.port.DeleteCustomerPort
import com.soat220.lanchonete.erp.port.FindCustomerByCpfPort
import org.springframework.stereotype.Service

@Service
class DeleteCustomerAdapter (
    private val customerRepository: CustomerRepository,
    private val findCustomerByCpfPort: FindCustomerByCpfPort
) : DeleteCustomerPort {

    override fun execute(customer: Customer) {

        val customerEntity = findCustomerByCpfPort.execute(customer.cpf!!).orThrow()

        customerRepository.deleteById(customerEntity.id!!)
    }
}