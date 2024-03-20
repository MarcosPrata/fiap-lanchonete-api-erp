package com.soat220.lanchonete.erp.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Result

interface CreateCustomerPort {

    fun execute(customer: Customer): Result<Customer, DomainException>
}