package com.cyborg.common.data.local

import com.squareup.sqldelight.db.SqlDriver

actual fun getSqlDriver(databaseName:String):SqlDriver= throw UninitializedPropertyAccessException("Android SqlDriver must be provided")