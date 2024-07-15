package com.example.amphibians.data

import com.example.amphibians.network.AmphibianApiService
import com.example.amphibians.network.AmphibianData

interface AmphibianDataRepository{
    suspend fun getAmphibiansData(): List<AmphibianData>
}

class NetworkAmphibianDataRepository(
    private  val amphibianApiService: AmphibianApiService
): AmphibianDataRepository{
    override suspend fun getAmphibiansData(): List<AmphibianData> {
        return amphibianApiService.getAmphibiansData()
    }
}