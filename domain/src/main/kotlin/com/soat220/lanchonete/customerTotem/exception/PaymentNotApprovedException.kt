package com.soat220.lanchonete.customerTotem.exception

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode

class PaymentNotApprovedException(message: String, errorCode: ErrorCode) :
    DomainException(
        message = message,
        errorCode = errorCode
    )