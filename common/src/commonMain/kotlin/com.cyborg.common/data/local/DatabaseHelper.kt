package com.cyborg.common.data.local

import com.cyborg.common.sql.CurrencyDatabase
import com.squareup.sqldelight.db.SqlDriver

expect fun getSqlDriver(databaseName: String): SqlDriver

class DatabaseHelper(
    databaseName: String,
    sqlDriver: SqlDriver?
) {

    private val driver: SqlDriver = sqlDriver ?: getSqlDriver(databaseName)
    val database: CurrencyDatabase = CurrencyDatabase(driver)
}