package com.my.cvapp.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by Tejas Shah on 25/03/19.
 */
fun showToast(context: Context?, strError: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, strError, length).show()
}