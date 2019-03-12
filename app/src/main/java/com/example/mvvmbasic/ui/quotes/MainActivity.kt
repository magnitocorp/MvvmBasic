package com.example.mvvmbasic.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmbasic.R
import com.example.mvvmbasic.data.Quote
import com.example.mvvmbasic.utilities.InjectUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUI()
    }

    private fun initializeUI() {
        val factory = InjectUtils.provideQuoteViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(QuotesViewModel::class.java)

        viewModel.getQuote().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()

            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }

            textView_quotes.text = stringBuilder.toString()
        })

        button_submit_quote.setOnClickListener {
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
            viewModel.addQuote(quote)
            editText_author.setText("")
            editText_quote.setText("")
        }
    }
}
