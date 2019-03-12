package com.example.mvvmbasic.utilities

import com.example.mvvmbasic.data.FakeDatabase
import com.example.mvvmbasic.data.QuoteRepository
import com.example.mvvmbasic.ui.quotes.QuotesViewModelFactory

object InjectUtils {

    fun provideQuoteViewModelFactory() : QuotesViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}