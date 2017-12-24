package info.raryosu.android.calc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null
    private var editText: EditText? = null
    internal lateinit var button: Button

    private val buttonListener = View.OnClickListener {
        recentOperator = R.id.button_equal
        result = 0.0
        isOperatorKeyPushed = false

        textView!!.text = ""
        editText!!.setText("")
    }

    private var recentOperator = R.id.button_equal
    private var result: Double = 0.toDouble()
    private var isOperatorKeyPushed: Boolean = false

    private val buttonNumberListener = View.OnClickListener { v ->
        val button = v as Button

        if (isOperatorKeyPushed) {
            editText!!.setText(button.text)
        } else {
            editText!!.append(button.text)
        }

        isOperatorKeyPushed = false
    }

    private val buttonOperatorListener = View.OnClickListener { v ->
        val operatorButton = v as Button
        val value = java.lang.Double.parseDouble(editText!!.text.toString())
        if (recentOperator == R.id.button_equal) {
            result = value
        } else {
            result = calc(recentOperator, result, value)
            editText!!.setText(result.toString())
        }

        recentOperator = operatorButton.id
        textView!!.text = operatorButton.text
        isOperatorKeyPushed = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textview) as TextView
        editText = findViewById(R.id.edittext) as EditText
        button = findViewById(R.id.button) as Button
        button.setOnClickListener(buttonListener)

        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener)
        findViewById(R.id.button_dot).setOnClickListener(buttonNumberListener)

        findViewById(R.id.button_add).setOnClickListener(buttonOperatorListener)
        findViewById(R.id.button_subtract).setOnClickListener(buttonOperatorListener)
        findViewById(R.id.button_multiply).setOnClickListener(buttonOperatorListener)
        findViewById(R.id.button_divide).setOnClickListener(buttonOperatorListener)
        findViewById(R.id.button_equal).setOnClickListener(buttonOperatorListener)
    }

    internal fun calc(operator: Int, value1: Double, value2: Double): Double {
        return when (operator) {
            R.id.button_add -> value1 + value2
            R.id.button_subtract -> value1 - value2
            R.id.button_multiply -> value1 * value2
            R.id.button_divide -> value1 / value2
            else -> value1
        }
    }
}
