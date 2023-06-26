package at.ac.fhcampuswien.muketa.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.fhcampuswien.muketa.models.FlagQuizModel
import at.ac.fhcampuswien.muketa.models.QuizModel
import at.ac.fhcampuswien.muketa.repository.QuizRepository
import kotlinx.coroutines.launch

class QuizViewModel(private val quizRepository: QuizRepository = QuizRepository()) : ViewModel() {

    private val mutableLiveData = MutableLiveData<List<QuizModel>>()
    val liveDataList: LiveData<List<QuizModel>> get() = mutableLiveData

    init {
        val flow = quizRepository.getQuiz()
        viewModelScope.launch {
            flow.collect {
                mutableLiveData.postValue(it)
            }
        }
    }

}