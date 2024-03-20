package com.soat220.lanchonete.erp.driver.rest.dto.request

import com.soat220.lanchonete.common.model.Customer

class CreateCustomerRequest (
    val name: String,
    val email: String,
    val cpf: String
) {
    fun toDomain() = Customer(
        name = name,
        email = email,
        cpf = cpf
    )
}