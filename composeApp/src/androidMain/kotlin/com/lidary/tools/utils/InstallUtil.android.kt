package com.lidary.tools.utils



/**
 * @Author : lidary-byte
 * @CreateData : 2025/2/14
 * @Description:
 */
class AndroidInstallApps : InstallApps {
    override val appsName: List<String>
        get() = emptyList<String>()
}

actual fun getInstallApps(): InstallApps {
    return AndroidInstallApps()
}


