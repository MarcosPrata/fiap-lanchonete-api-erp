package com.soat220.lanchonete.erp.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.erp.port.CreateCustomerPort
import javax.inject.Named

@Named
class CreateCustomer (
    private val createCustomerPort: CreateCustomerPort
){

    fun execute(customer: Customer): Result<Customer, DomainException> {
        return createCustomerPort.execute(customer)
    }
}