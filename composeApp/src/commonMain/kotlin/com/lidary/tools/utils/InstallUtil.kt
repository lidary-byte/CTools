package com.lidary.tools.utils

/**
 * @Author : lidary-byte
 * @CreateData : 2025/2/14
 * @Description:
 */
interface InstallApps {
    val appsName: List<String>
}

expect fun getInstallApps(): InstallApps
