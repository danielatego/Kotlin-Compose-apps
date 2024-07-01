package com.example.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentWord: String

    var userGuess by mutableStateOf("")
        private set

    //Set of words used in the game
    private var usedWords: MutableSet<String> = mutableSetOf()

    private fun pickRandomWordAndShuffle(): String{
        // Continue picking a new random word until you get one that hasn't been used before
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)){
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while ( String(tempWord).equals(word)){
            tempWord.shuffle()
        }
        return String(tempWord)
    }
    private fun updateGameState(updatedScore: Int){
       if (usedWords.size == MAX_NO_OF_WORDS){
           //Last round in the game, update isGameOver to true, don't pick a new word
           _uiState.update {currentState->
               currentState.copy(
                   isGuessedWordWrong = false,
                   score = updatedScore,
                   isGameOver = true,
               )
           }
    } else {
        // Normal round in the game
        _uiState.update { currentState ->
           currentState.copy(
               isGuessedWordWrong = false,
               currentScrambledWord = pickRandomWordAndShuffle(),
               score = updatedScore,
               currentWordCount = currentState.currentWordCount.inc(),
           )
        }
       }
    }
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessedWord: String){
        userGuess = guessedWord
    }

    fun checkUserGuess() {
        if(userGuess.equals(currentWord, ignoreCase = true)){
            //User's guess is correct, increase the score
            //and call updateGameState() to prepare the game for the next round
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else{
            //User's guess is wrong, show and error
            _uiState.update { currentState->
                currentState.copy(isGuessedWordWrong = true)
            }

        }
        //Reset user guess
        updateUserGuess("")
    }
    fun skipWord() {
        updateGameState(_uiState.value.score)
        // Reset user guess
        updateUserGuess("")
    }

    init {
        resetGame()
    }
}