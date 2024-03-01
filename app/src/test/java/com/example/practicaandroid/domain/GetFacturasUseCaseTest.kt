package com.example.practicaandroid.domain

import com.example.practicaandroid.data.FacturasRepository
import com.example.practicaandroid.data.model.FacturaModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GetFacturasUseCaseTest {
    @Test
    suspend fun whenFacturasReturnedAreEmpty() {
        val mockFacturasService = Mockito.mock(FacturasRepository::class.java)
        Mockito.`when`(mockFacturasService.getAllFacturasFromRetromock()).thenReturn(emptyList())
        val useCase = GetFacturasUseCase(mockFacturasService)
        Assertions.assertEquals(emptyList<FacturaModel>(), useCase.invoke(true))
    }

}
