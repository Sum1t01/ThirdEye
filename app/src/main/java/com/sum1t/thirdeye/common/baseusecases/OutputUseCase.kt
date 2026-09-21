package com.sum1t.thirdeye.common.baseusecases

interface OutputUseCase<out outpuT> {
    fun invoke(): outpuT
}
