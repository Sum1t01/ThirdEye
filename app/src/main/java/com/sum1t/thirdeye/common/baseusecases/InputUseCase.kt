package com.sum1t.thirdeye.common.baseusecases

interface InputUseCase<in inpuT> {
    fun invoke(inpuT: inpuT)
}
