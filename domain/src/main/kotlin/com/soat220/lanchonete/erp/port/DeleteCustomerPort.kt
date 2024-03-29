package com.soat220.lanchonete.erp.port

import com.soat220.lanchonete.common.model.Customer

interface DeleteCustomerPort {

    fun execute(customer: Customer)
}