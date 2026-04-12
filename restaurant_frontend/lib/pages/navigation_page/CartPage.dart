import 'package:flutter/material.dart';

class Cartpage extends StatefulWidget {

  final String username;
  const Cartpage({super.key, required this.username});

  @override
  State<Cartpage> createState() => _CartpageState();
}

class _CartpageState extends State<Cartpage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar:
      AppBar(
        title: Text("Sepetim"),
      ),
      body: Center(
        child: Column(
          children: [
            Text(widget.username)
          ],
        ),
      ),
    );
  }
}
