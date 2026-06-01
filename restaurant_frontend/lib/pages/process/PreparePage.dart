import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';
import 'package:restaurant_frontend/pages/home/home_page.dart';

class Preparepage extends StatefulWidget {

  String username;
  Preparepage({
    super.key,
    required this.username,
  });

  @override
  State<Preparepage> createState() => _PreparepageState();
}

class _PreparepageState extends State<Preparepage> {
  
  Future<void> prepare() async {
    await Future.delayed(const Duration(seconds: 3));
    Navigator.pushAndRemoveUntil(
        context,
        MaterialPageRoute(builder: (context) => HomePage(username: widget.username)),
        (route) => false);
  }
  
  @override
  void initState() {
    prepare();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(mainAxisAlignment: MainAxisAlignment.center,
          children: [
              Lottie.asset("asset/prepare.json" , width: 250 , height: 250),
              Text("Sipariş Alınıyor.." , style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),)
          ],
        ),
      ),
    );
  }
}
