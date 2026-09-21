package com.sum1t.thirdeye.common.baseusecases

interface BaseUseCase<in inpuT, out outpuT> {
    fun invoke(inpuT: inpuT): outpuT
}
