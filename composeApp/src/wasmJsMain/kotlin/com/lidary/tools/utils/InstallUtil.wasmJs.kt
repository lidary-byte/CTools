package com.lidary.tools.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * @Author : lidary-byte
 * @CreateData : 2025/2/14
 * @Description:
 */
class WasmInstallApps : InstallApps {
    override val appsName: List<String>
        get() = emptyList<String>()
}

actual suspend fun getInstallApps(): InstallApps =  withContext(Dispatchers.Default){
    return@withContext WasmInstallApps()
}


