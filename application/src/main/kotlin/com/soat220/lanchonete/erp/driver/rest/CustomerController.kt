package com.soat220.lanchonete.erp.driver.rest

import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.erp.driver.rest.dto.request.CreateCustomerRequest
import com.soat220.lanchonete.erp.driver.rest.dto.request.DeleteCustomerRequest
import com.soat220.lanchonete.erp.usecase.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/erp/customers"], produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE])
class CustomerController(
    private val findCustomers: FindAllCustomers,
    private val findCustomerById: FindCustomerById,
    private val findCustomerByCpf: FindCustomerByCpf,
    private val createCustomer: CreateCustomer,
    private val deleteCustomer: DeleteCustomer
) {
    @GetMapping
    fun findCustomers() = findCustomers.execute().orThrow()

    @GetMapping("/{customerId}")
    fun findCustomerById(@PathVariable customerId: Long) = findCustomerById.execute(customerId).orThrow()

    @PostMapping
    fun createCustomer(@RequestBody createCustomerRequest: CreateCustomerRequest) =
        createCustomer.execute(createCustomerRequest.toDomain()).orThrow()

    @DeleteMapping
    fun deleteCustomer(@RequestBody deleteCustomerRequest: DeleteCustomerRequest) =
        deleteCustomer.execute(deleteCustomerRequest.toDomain())

    @GetMapping("/find")
    fun findCustomerByCpf(@RequestParam cpf: String): ResponseEntity<Customer> {
        return try {
            ResponseEntity(findCustomerByCpf.execute(cpf).orThrow(), HttpStatus.OK)
        } catch (_: Exception) {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }
}