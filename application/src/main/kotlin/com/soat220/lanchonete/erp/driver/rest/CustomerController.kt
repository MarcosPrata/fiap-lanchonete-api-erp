package com.soat220.lanchonete.erp.driver.rest

import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.erp.driver.rest.dto.request.CreateCustomerRequest
import com.soat220.lanchonete.erp.usecase.CreateCustomer
import com.soat220.lanchonete.erp.usecase.FindAllCustomers
import com.soat220.lanchonete.erp.usecase.FindCustomerByCpf
import com.soat220.lanchonete.erp.usecase.FindCustomerById
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/erp/customers"], produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE])
class CustomerController(
    private val findCustomers: FindAllCustomers,
    private val findCustomerById: FindCustomerById,
    private val findCustomerByCpf: FindCustomerByCpf,
    private val createCustomer: CreateCustomer
) {
    @GetMapping
    fun findCustomers() = findCustomers.execute().orThrow()

    @GetMapping("/{customerId}")
    fun findCustomerById(@PathVariable customerId: Long) = findCustomerById.execute(customerId).orThrow()

    @PostMapping
    fun createCustomer(@RequestBody createCustomerRequest: CreateCustomerRequest) =
        createCustomer.execute(createCustomerRequest.toDomain()).orThrow()

    fun deleteCustomer(@RequestBody deleteCustomerRequest: CreateCustomerRequest) {

    }

    @GetMapping("/find")
    fun findCustomerByCpf(@RequestParam cpf: String): ResponseEntity<Customer> {
        return try {
            ResponseEntity(findCustomerByCpf.execute(cpf).orThrow(), HttpStatus.OK)
        } catch (_: Exception) {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }
}