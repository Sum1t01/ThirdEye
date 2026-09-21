package com.sum1t.thirdeye.common.baseusecases

interface SuspendingOutputUseCase<out outpuT> {
    suspend fun invoke(): outpuT
}
