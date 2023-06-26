package at.ac.fhcampuswien.muketa.repository

import android.util.Log
import at.ac.fhcampuswien.muketa.helper.HelperClass
import at.ac.fhcampuswien.muketa.models.FlagQuizModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FlagRepository {

    private val list by lazy { ArrayList<FlagQuizModel>() }

    fun getFlagQuiz(): Flow<List<FlagQuizModel>> = callbackFlow {

        val firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection(HelperClass.mySelection)

        collectionRef.get()
            .addOnSuccessListener { querySnapshot ->
                list.clear()
                for (document in querySnapshot.documents) {
                    val question = document.get("question").toString()
                    val url = document.get("url").toString()
                    val answer1 = document.get("answer1").toString()
                    val answer2 = document.get("answer2").toString()
                    val answer3 = document.get("answer3").toString()
                    val answer4 = document.get("answer4").toString()
                    val answer5 = document.get("answer5").toString()

                    val flagData = FlagQuizModel(
                        question,
                        url,
                        answer1,
                        answer2,
                        answer3,
                        answer4,
                        answer5
                    )
                    list.add(flagData)
                }
                trySend(list)
            }
            .addOnFailureListener { exception ->
                // Handle any errors that occurred while fetching the data
            }
        awaitClose { this.cancel() }
    }
}