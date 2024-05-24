package com.app.meditation.domain.usecase

import com.app.meditation.domain.repository.TuneRepository
import com.app.meditation.ui.screen.tuneList.DataTunes
import javax.inject.Inject

class GetTuneListUseCase @Inject constructor(val tuneRepository: TuneRepository) {

    suspend fun getTunes(): MutableList<DataTunes> {
        return tuneRepository.getTunes()
    }
}