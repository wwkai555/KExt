package com.example.kevin.kext.util

import android.util.Log

/**
 * Created by kevin on 28/11/2017.
 */
fun illegal(msg: String = ""): Nothing = throw RuntimeException(msg)

fun <T> T.print(msg: String = "") = apply { Log.d("KGL", "--->>$msg: $this") }

fun <T> T.sysPrint(msg: String = "") = apply { System.out.print("--->>$msg: $this \n") }