package com.example.simplestopwatch.utils

fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')