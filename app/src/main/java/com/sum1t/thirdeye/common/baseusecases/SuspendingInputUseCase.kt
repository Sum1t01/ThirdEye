package com.sum1t.thirdeye.common.baseusecases

interface SuspendingInputUseCase<in inpuT> {
    suspend fun invoke(inpuT: inpuT)
}
