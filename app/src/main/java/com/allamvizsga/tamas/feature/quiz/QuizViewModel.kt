package com.allamvizsga.tamas.feature.quiz

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.allamvizsga.tamas.model.Answer
import com.allamvizsga.tamas.model.Question

class QuizViewModel(val question: Question) : ViewModel() {

    val questionText = ObservableField<SpannableString>(SpannableString(question.text))
    private var selectedAnswer: Answer? = null

    fun onCheckedChanged(position: Int) {
        selectedAnswer = question.answers[position].also { answer ->
            val start = question.text.indexOf("_")
            val spannable = SpannableString(question.text.replace("__________", answer.text))
            spannable.setSpan(UnderlineSpan(), start, start + answer.text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            questionText.set(spannable)
        }
    }

    fun isAnswerCorrect() = selectedAnswer?.correct
}