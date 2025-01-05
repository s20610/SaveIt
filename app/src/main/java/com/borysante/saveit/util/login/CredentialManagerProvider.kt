package com.borysante.saveit.util.login

import android.content.Context
import androidx.credentials.CredentialManager

object CredentialManagerProvider {
    fun getCredentialManager(context: Context) = CredentialManager.create(context)
}