// ignore_for_file: use_build_context_synchronously

import 'dart:convert';

import 'package:datacrm_app/domain/models/login_model.dart';
import 'package:datacrm_app/ui/screens/home_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  var channel = const MethodChannel("connectFlutterAndKotlin");

  final _formKey = GlobalKey<FormState>();
  final userName = TextEditingController();
  final password = TextEditingController();

  Future<void> _handleLogin(String userName) async {
    try {
      var res = await channel
          .invokeMethod("login", {'username': userName, 'operation': "login"});
      var loginResponse = LoginResponse.fromJson(jsonDecode(res));
      final currentContext = context;
      //print(loginResponse.result!.sessionName);
      if (loginResponse.success) {
        Navigator.push(
          currentContext,
          MaterialPageRoute(
              builder: (context) =>
                  HomeScreen(sessionName: loginResponse.result!.sessionName)),
        );
      } else {
        print('Login failed');
        // Show SnackBar
        ScaffoldMessenger.of(currentContext).showSnackBar(
          const SnackBar(
            content: Text('Usuario no encontrado'),
            duration: Duration(seconds: 2),
          ),
        );
      }
    } catch (e) {
      print(e.toString());
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              "Iniciar sesión en\nDataCRM",
              style: TextStyle(
                fontSize: 32,
                fontWeight: FontWeight.w600,
              ),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 30),
            Form(
              key: _formKey,
              child: TextFormField(
                controller: userName,
                maxLength: 30,
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'campo obligatorio';
                  }
                  return null;
                },
                decoration: const InputDecoration(
                  labelText: "Usuario",
                  border: OutlineInputBorder(),
                ),
              ),
            ),
            ElevatedButton(
              onPressed: () {
                if (_formKey.currentState?.validate() ?? false) {
                  FocusScope.of(context).unfocus();
                  _handleLogin(userName.text);
                }
              },
              child: const Text('Ingresar'),
            ),
            const SizedBox(height: 30),
          ],
        ),
      ),
    );
  }
}
