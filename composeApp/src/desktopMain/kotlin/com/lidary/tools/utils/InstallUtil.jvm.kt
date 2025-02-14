package com.lidary.tools.utils

import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * @Author : lidary-byte
 * @CreateData : 2025/2/14
 * @Description:
 */
class JVMInstallApps : InstallApps {
    override val appsName: List<String>
        get() = listInstalledApps()
}

//class JVMPlatform: Platform {
//    override val name: String = "Java ${System.getProperty("java.version")}"
//}

actual fun getInstallApps(): InstallApps {
    return JVMInstallApps()
}



fun listInstalledApps(): List<String> {
//            val appsDirectory = File("/Applications")
//
//            // 检查该路径是否存在并且是目录
//            if (!appsDirectory.exists() || !appsDirectory.isDirectory) {
//                return emptyList()
//            }
//
//            // 列出目录下所有的文件/文件夹，筛选出 .app 文件夹
//            return appsDirectory.listFiles { _, name -> name.endsWith(".app") }?.map { it.name } ?: emptyList()
    val process = ProcessBuilder("system_profiler", "SPApplicationsDataType")
        .redirectErrorStream(true)
        .start()

    val reader = BufferedReader(InputStreamReader(process.inputStream))
    val apps = mutableListOf<String>()

    var line: String?
    while (reader.readLine().also { line = it } != null) {
        // 解析 SPApplicationsDataType 输出中的应用程序名称
        if (line!!.contains("Location:")) {
            val appName = line.substringAfter("Location:").trim()
            if (appName.endsWith(".app")) {
                apps.add(appName)
            }
        }
    }

    process.waitFor()
    return apps
}
