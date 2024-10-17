package com.example.datacrm_app

import android.widget.Toast
import com.example.datacrm_app.api.ApiCalls
import com.example.datacrm_app.models.LoginModel
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.Dispatchers
import com.example.datacrm_app.utils.KeyGenerator
import com.google.gson.Gson
import io.flutter.plugin.common.MethodCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity: FlutterActivity() {
    private val channel = "connectFlutterAndKotlin"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        val methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channel)

        methodChannel.setMethodCallHandler { call, result ->
            CoroutineScope(Dispatchers.Main).launch {
                when (call.method) {
                    "login" -> handleLogin(call, result)
                    "getContacts" -> handleGetContacts(call, result)
                    else -> result.notImplemented()
                }
            }
        }
    }

    suspend fun handleLogin(call: MethodCall, result: MethodChannel.Result) {
        val apiResponse: LoginModel.ApiResponseGetKey?
        val loginResponse: LoginModel.ApiResponseLogin?
        val keyGenerator = KeyGenerator()
        val username = call.argument<String>("username")
        val operation = call.argument<String>("operation")
        val gson = Gson()

        try {
            apiResponse = ApiCalls.RequestManager.api.getKey("getchallenge", "prueba")

            if (apiResponse?.success == true) {
                val generatedKey = keyGenerator.generateKey(apiResponse.result.token)
                loginResponse = ApiCalls.RequestManager.api.login(operation!!, username!!, generatedKey)

                val jsonResponse = gson.toJson(loginResponse)
                result.success(jsonResponse)
            } else {
                result.error("GET_KEY_ERROR", "Error with getKey", null)
            }
        } catch (e: Exception) {
            result.error("REQUEST_ERROR", "Error in request: ${e.message}", null)
        }
    }

    suspend fun handleGetContacts(call: MethodCall, result: MethodChannel.Result) {
        val sessionName = call.argument<String>("sessionName")
        val gson = Gson()

        try {
            Toast.makeText(this, sessionName, Toast.LENGTH_LONG).show()

            val userDetailsResponse = ApiCalls.RequestManager.api.getContacts("query", sessionName!!, "select * from Contacts;")
            val jsonResponse = gson.toJson(userDetailsResponse)

            result.success(jsonResponse)
        } catch (e: Exception) {
            result.error("USER_DETAILS_ERROR", "Error retrieving user details: ${e.message}", null)
        }
    }
}
