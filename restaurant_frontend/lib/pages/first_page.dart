import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';

class FirstPage extends StatefulWidget {
  const FirstPage({super.key});

  @override
  State<FirstPage> createState() => _FirstPageState();
}

class _FirstPageState extends State<FirstPage> {

  Future<void> animation() async {
    await Future.delayed(const Duration(seconds: 5));
    Navigator.pushReplacementNamed(context, "/register");
  }

  @override
  void initState() {
    animation();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Center(
        child: Row( mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Column(mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Container(
                    height: 250,
                    width: 250,
                    child: Lottie.asset("asset/Fast food.json" , width: 300, height: 300)),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
