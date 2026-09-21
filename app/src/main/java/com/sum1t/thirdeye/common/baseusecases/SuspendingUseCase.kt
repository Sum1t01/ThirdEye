package com.sum1t.thirdeye.common.baseusecases

interface SuspendingUseCase<in inpuT, out outpuT> {
    suspend fun invoke(inpuT: inpuT): outpuT
}
