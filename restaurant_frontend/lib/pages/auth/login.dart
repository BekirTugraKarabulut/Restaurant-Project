import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';
import 'package:restaurant_frontend/pages/home/home_page.dart';
import 'package:restaurant_frontend/services/auth/LoginService.dart';

class Login extends StatefulWidget {
  const Login({super.key});

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {

  var username = TextEditingController();
  var password = TextEditingController();
  final LoginService loginService = LoginService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SingleChildScrollView(
        child: Center(
          child: Column(
            children: [
              Padding(
                padding: const EdgeInsets.only(top: 50),
                child: Lottie.asset("asset/order food now delivery.json" , width: 250, height: 250),
              ),
              Padding(
                padding: const EdgeInsets.all(10.0),
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(top: 12),
                      child: TextField(
                        controller: username,
                        style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),
                        decoration: InputDecoration(
                          suffixIcon: Icon(Icons.mail, color: Colors.black,),
                          hintStyle: TextStyle(color: Colors.black ),
                          hintText: "Email",
                          enabledBorder: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20),
                              borderSide: const BorderSide(
                                  color: Colors.red, width: 2)
                          ),
                          disabledBorder: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20),
                              borderSide: const BorderSide(
                                  color: Colors.red, width: 2)
                          ),
                          focusedBorder: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20),
                              borderSide: const BorderSide(
                                  color: Colors.red, width: 2)
                          ),
                        ),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 12),
                      child: TextField(
                        obscureText: true,
                        controller: password,
                        style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),
                        decoration: InputDecoration(
                          suffixIcon: Icon(Icons.key, color: Colors.black,),
                          hintStyle: TextStyle(color: Colors.black ),
                          hintText: "Password",
                          enabledBorder: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20),
                              borderSide: const BorderSide(
                                  color: Colors.red, width: 2)
                          ),
                          disabledBorder: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20),
                              borderSide: const BorderSide(
                                  color: Colors.red, width: 2)
                          ),
                          focusedBorder: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20),
                              borderSide: const BorderSide(
                                  color: Colors.red, width: 2)
                          ),
                        ),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 16),
                      child: ElevatedButton(style: ButtonStyle(
                        backgroundColor: WidgetStatePropertyAll(
                          Colors.red
                        ),
                        shape: WidgetStatePropertyAll(
                            RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(18)
                            )),
                      ),onPressed: () async {

                        bool response = await loginService.login(username.text, password.text);
                        if(response){
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(content: Text("Giriş Başarılı"),
                            action: SnackBarAction(label: "Tamam", onPressed: (){}),
                            )
                          );
                          Navigator.pushAndRemoveUntil(context,
                              MaterialPageRoute(builder:(context) => HomePage(username: username.text) ),
                              (route) => false);
                        }else{
                          ScaffoldMessenger.of(context).showSnackBar(
                              SnackBar(content: Text("Giriş Başarısız"),
                                action: SnackBarAction(label: "Tamam", onPressed: (){}),
                              )
                          );
                          return;
                        }

                      }, child: Text("Giriş Yap" , style: TextStyle(color: Colors.white , fontWeight: FontWeight.bold),)),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 10),
                      child: Row(mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("Hesabın yok mu ? "),
                          GestureDetector(
                              onTap: () => Navigator.pushReplacementNamed(context, "/register"),
                              child: Text("Kayıt Ol" , style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),))
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
