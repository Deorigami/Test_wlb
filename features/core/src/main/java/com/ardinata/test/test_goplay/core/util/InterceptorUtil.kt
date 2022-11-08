package com.ardinata.test.test_goplay.core.util

import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

object InterceptorUtil {
    fun bodyToString(request: RequestBody?): String {
        return try {
            val buffer = Buffer()
            if (request != null) request.writeTo(buffer) else return ""
            buffer.readUtf8()
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }
    fun bodyToStringWithoutLang(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null) request.writeTo(buffer) else return ""
            val reqString = buffer.readUtf8()
            val req = JSONObject(reqString)
            req.remove("lang")
            return req.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }catch (e: JSONException) {
            e.printStackTrace()
            return ""
        }
    }

    fun bodyToString(response: ResponseBody?): String {
        return try {
            if (response != null) {
                val source = response.source()
                source.request(Long.MAX_VALUE)
                val buffer = source.buffer()
                val contentLength = response.contentLength()
                val contentType = response.contentType()
                val charset: Charset = contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
                if (contentLength != 0L) {
                    buffer.clone().readString(charset)
                } else
                    ""
            } else
                ""
        } catch (e: Exception) {
            e.printStackTrace();
            ""
        }
    }
}