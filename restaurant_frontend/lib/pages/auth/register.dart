import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';
import 'package:restaurant_frontend/services/auth/RegisterService.dart';

class Register extends StatefulWidget {
  const Register({super.key});

  @override
  State<Register> createState() => _RegisterState();
}

class _RegisterState extends State<Register> {

  var username = TextEditingController();
  var name = TextEditingController();
  var password = TextEditingController();
  final RegisterService registerService = RegisterService();

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
                  child: Lottie.asset("asset/register.json" , width: 250, height: 250),
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
                        controller: name,
                        style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),
                        decoration: InputDecoration(
                          suffixIcon: Icon(Icons.account_box_rounded, color: Colors.black,),
                          hintStyle: TextStyle(color: Colors.black ),
                          hintText: "Name",
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
                      child: Container(
                        width: 120,
                        height: 50,
                        child: ElevatedButton(style: ButtonStyle(
                          backgroundColor: WidgetStatePropertyAll(
                            Colors.red
                          ),
                          shape: WidgetStatePropertyAll(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(18)
                          )),
                        ),onPressed: () async{

                          bool result = await registerService.register(username.text, name.text, password.text);
                          if(result){
                            ScaffoldMessenger.of(context).showSnackBar(
                              SnackBar(content: Text("Kayıt Başarılı"),
                              action: SnackBarAction(label: "Tamam", onPressed: (){}),
                              )
                            );
                          }else{
                            ScaffoldMessenger.of(context).showSnackBar(
                                SnackBar(content: Text("Kayıt Başarısız"),
                                  action: SnackBarAction(label: "Tamam", onPressed: (){}),
                                )
                            );
                            return;
                          }

                        }, child: Text("Kayıt Ol" , style: TextStyle(color: Colors.white , fontWeight: FontWeight.bold),)),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 10),
                      child: Row(mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("Zaten bir hesabım var ? "),
                          GestureDetector(
                            onTap: () => Navigator.pushReplacementNamed(context, "/login"),
                              child: Text("Giris Yap" , style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),))
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
