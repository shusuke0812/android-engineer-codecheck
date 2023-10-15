package jp.co.yumemi.android.code_check.utility.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.closeKeyboard() {
    val system = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    system?.hideSoftInputFromWindow(view?.windowToken, 0)
}