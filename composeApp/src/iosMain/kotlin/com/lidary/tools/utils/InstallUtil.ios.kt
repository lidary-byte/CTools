package com.lidary.tools.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext


/**
 * @Author : lidary-byte
 * @CreateData : 2025/2/14
 * @Description:
 */
class IOSInstallApps : InstallApps {
    override val appsName: List<String>
        get() = emptyList<String>()
}

actual suspend fun getInstallApps(): InstallApps = withContext(Dispatchers.IO) {
    return@withContext IOSInstallApps()
}


