import 'package:flutter/material.dart';
import 'package:restaurant_frontend/pages/auth/login.dart';
import 'package:restaurant_frontend/pages/auth/register.dart';
import 'package:restaurant_frontend/pages/first_page/first_page.dart';
import 'package:restaurant_frontend/pages/home/home_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      routes:
      {
        "/home": (context) => const FirstPage(),
        "/register": (context) => const Register(),
        "/login": (context) => const Login(),
      },
      title: 'Flutter Demo',
      theme: ThemeData(

        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const FirstPage(),
    );
  }
}
