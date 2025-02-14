package com.lidary.tools.utils

import com.lidary.tools.entity.ApplicationInfoEntity

/**
 * @Author : lidary-byte
 * @CreateData : 2025/2/14
 * @Description:
 */
interface InstallApps {
    val appsName: List<ApplicationInfoEntity>
}

expect suspend fun getInstallApps(): InstallApps
