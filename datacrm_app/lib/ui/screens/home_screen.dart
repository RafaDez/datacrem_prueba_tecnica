import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomeScreen extends StatefulWidget {
  final String sessionName;
  const HomeScreen({super.key, required this.sessionName});

  @override
  State<HomeScreen> createState() => HomeScreenState();
}

class HomeScreenState extends State<HomeScreen> {
  var channel = const MethodChannel("connectFlutterAndKotlin");

  Future<void> _handleContacts(String sessionName) async {
    try {
      print(sessionName);
      var res = await channel
          .invokeMethod("getContacts", {'sessionName': sessionName});
      print('res: $res');
      print('res: ${res.runtimeType}');

      //var loginResponse = LoginResponse.fromJson(jsonDecode(res));
      //final currentContext = context;
      //print(loginResponse.result!.sessionName);
      // if (loginResponse.success) {
      //   Navigator.push(
      //     currentContext,
      //     MaterialPageRoute(builder: (context) => const HomeScreen()),
      //   );
      // } else {
      //   print('Login failed');
      //   // Show SnackBar
      //   ScaffoldMessenger.of(currentContext).showSnackBar(
      //     SnackBar(
      //       content: Text('Usuario no encontrado'),
      //       duration: Duration(seconds: 2),
      //     ),
      //   );
      // }
    } catch (e) {
      print(e.toString());
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('home'),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          ElevatedButton(
            onPressed: () {
              _handleContacts(widget.sessionName);
            },
            child: const Text('Cargar tabla de contactos'),
          ),
        ],
      ),
    );
  }
}
