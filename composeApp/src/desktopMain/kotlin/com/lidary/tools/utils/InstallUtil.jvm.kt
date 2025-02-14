package com.lidary.tools.utils

import com.lidary.tools.entity.ApplicationInfoEntity
import com.lidary.tools.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okio.*


/**
 * @Author : lidary-byte
 * @CreateData : 2025/2/14
 * @Description:
 */
class JVMInstallApps : InstallApps {
    override val appsName: List<ApplicationInfoEntity>
        get() = listInstalledApps()
}


actual suspend fun getInstallApps(): InstallApps = withContext(Dispatchers.IO) {

    return@withContext JVMInstallApps()
}



fun listInstalledApps(): List<ApplicationInfoEntity> {
    val process = ProcessBuilder("system_profiler", "-json", "-detailLevel", "mini", "SPApplicationsDataType")
        .redirectErrorStream(true)
        .start()

//    process.waitFor()

    val jsonOutput = process.inputStream.source().buffer().readUtf8()

    return json.parseToJsonElement(jsonOutput).jsonObject["SPApplicationsDataType"]?.jsonArray?.map {
        ApplicationInfoEntity(
            it.jsonObject["path"]?.jsonPrimitive?.content.orEmpty(),
            it.jsonObject["version"]?.jsonPrimitive?.content.orEmpty(),
            it.jsonObject["_name"]?.jsonPrimitive?.content.orEmpty()
        )
    } ?: emptyList()
}
