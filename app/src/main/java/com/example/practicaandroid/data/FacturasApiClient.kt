package com.example.practicaandroid.data

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.example.practicaandroid.model.respuestaApiFacturas
import retrofit2.Response
import retrofit2.http.GET

interface FacturasApiClient {
    @Mock
    @MockResponse(body = "{\n" +
            "    \"numFacturas\": 8,\n" +
            "    \"facturas\": [\n" +
            "        {\n" +
            "            \"descEstado\": \"Pendiente de pago\",\n" +
            "            \"importeOrdenacion\": 1.56,\n" +
            "            \"fecha\": \"07/02/2019\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"descEstado\": \"Pagada\",\n" +
            "            \"importeOrdenacion\": 25.14,\n" +
            "            \"fecha\": \"05/02/2019\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"descEstado\": \"Pagada\",\n" +
            "            \"importeOrdenacion\": 22.69,\n" +
            "            \"fecha\": \"08/01/2019\"\n" +
            "        }" +
            "   ]" +
            "}")
    @GET("/facturas")
    suspend fun getFacturas(): Response<respuestaApiFacturas>
}



/*

 */