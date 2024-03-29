package com.soat220.lanchonete.erp.usecase

import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.erp.port.DeleteCustomerPort
import javax.inject.Named

@Named
class DeleteCustomer(
    private val deleteCustomerPort: DeleteCustomerPort
) {

    fun execute(customer: Customer) {
        deleteCustomerPort.execute(customer)
    }
}