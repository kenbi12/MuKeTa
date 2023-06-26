package at.ac.fhcampuswien.muketa.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.fhcampuswien.muketa.models.FlagQuizModel
import at.ac.fhcampuswien.muketa.repository.FlagRepository
import kotlinx.coroutines.launch

class FlagQuizViewModel(private val flagRepository: FlagRepository = FlagRepository()) :
    ViewModel() {

    private val mutableLiveData = MutableLiveData<List<FlagQuizModel>>()
    val liveDataList: LiveData<List<FlagQuizModel>> get() = mutableLiveData

    init {
        val flow = flagRepository.getFlagQuiz()
        viewModelScope.launch {
            flow.collect {
                mutableLiveData.postValue(it)
            }
        }
    }
}