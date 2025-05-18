package com.borysante.saveit.util.formatter

import java.util.Locale

object AmountFormatter {
    fun Double.formatAmount() = String.format(Locale.getDefault(), "%.2f", this)
}