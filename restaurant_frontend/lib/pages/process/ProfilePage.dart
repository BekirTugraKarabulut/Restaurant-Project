import 'package:flutter/material.dart';
import 'package:restaurant_frontend/pages/auth/login.dart';
import 'package:restaurant_frontend/pages/process/CustomerAddressPage.dart';
import 'package:restaurant_frontend/services/customer/GetCustomerInfo.dart';

class Profilepage extends StatefulWidget {

  final username;
  const Profilepage({super.key, this.username});

  @override
  State<Profilepage> createState() => _ProfilepageState();
}

class _ProfilepageState extends State<Profilepage> {

  final GetCustomerInfo getCustomerInfo = GetCustomerInfo();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.red,
        centerTitle: true,
        actions: [
          Padding(
            padding: const EdgeInsets.only(right: 5),
            child: IconButton(onPressed: (){
                Navigator.push(context, MaterialPageRoute(builder: (context) => Customeraddresspage(username: widget.username,)));
            }, icon: Icon(Icons.location_on, color: Colors.white,)),
          ),
          Padding(
            padding: const EdgeInsets.only(right: 5),
            child: IconButton(onPressed: (){
              Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => Login()));
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(content: Text("Çıkış Yaptınız"),
                action: SnackBarAction(label: "Tamam", onPressed: (){}),
                )
              );
              return;
            }, icon: Icon(Icons.logout, color: Colors.white,)),
          )
        ],
        leading: IconButton(onPressed: (){
          Navigator.pop(context);
        }, icon: Icon(Icons.arrow_back_ios_new_rounded , color: Colors.white,)),
        title: Text("Profil" , style: TextStyle(color: Colors.white),),
      ),
      body: SingleChildScrollView(
        child: Center(
          child: Column(
            children: [
                FutureBuilder<Map<String , dynamic>>(
                    future: getCustomerInfo.getCustomerInfo(widget.username),
                    builder: (context, snapshot) {
                      if(snapshot.hasData){
                        var customerInfo = snapshot.data!;
                        String phoneNumber = "Numara giriniz";
                        return Column(
                          children: [
                              Image.asset("images/account.jpeg" , width: 200, height: 200 ,),
                              SizedBox(height: 20,),
                              Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: TextField(
                                  decoration: InputDecoration(
                                    suffixIcon: Icon(Icons.mail , color:  Colors.red,),
                                    hintStyle: TextStyle(color: Colors.grey),
                                    hintText: customerInfo["username"],
                                    disabledBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.all(Radius.circular(20)),
                                      borderSide: BorderSide(
                                        color: Colors.red
                                      )
                                    ),
                                    focusedBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.all(Radius.circular(20)),
                                      borderSide: BorderSide(
                                        color: Colors.red
                                      )
                                    ),
                                    enabledBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.all(Radius.circular(20)),
                                      borderSide: BorderSide(
                                        color: Colors.red
                                      )
                                    )
                                  ),
                                  enabled: false,
                                ),
                              ),
                            Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: TextField(
                                decoration: InputDecoration(
                                    suffixIcon: Icon(Icons.account_box_rounded , color:  Colors.red,),
                                    hintStyle: TextStyle(color: Colors.black),
                                    hintText: customerInfo["name"],
                                    disabledBorder: OutlineInputBorder(
                                        borderRadius: BorderRadius.all(Radius.circular(20)),
                                        borderSide: BorderSide(
                                            color: Colors.red
                                        )
                                    ),
                                    focusedBorder: OutlineInputBorder(
                                        borderRadius: BorderRadius.all(Radius.circular(20)),
                                        borderSide: BorderSide(
                                            color: Colors.red
                                        )
                                    ),
                                    enabledBorder: OutlineInputBorder(
                                        borderRadius: BorderRadius.all(Radius.circular(20)),
                                        borderSide: BorderSide(
                                            color: Colors.red
                                        )
                                    )
                                ),
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: TextField(
                                decoration: InputDecoration(
                                    suffixIcon: Icon(Icons.phone , color:  Colors.red,),
                                    hintStyle: TextStyle(color: Colors.black),
                                    hintText: customerInfo["phoneNumber"] == "null" ? phoneNumber : customerInfo["phoneNumber"],
                                    disabledBorder: OutlineInputBorder(
                                        borderRadius: BorderRadius.all(Radius.circular(20)),
                                        borderSide: BorderSide(
                                            color: Colors.red
                                        )
                                    ),
                                    focusedBorder: OutlineInputBorder(
                                        borderRadius: BorderRadius.all(Radius.circular(20)),
                                        borderSide: BorderSide(
                                            color: Colors.red
                                        )
                                    ),
                                    enabledBorder: OutlineInputBorder(
                                        borderRadius: BorderRadius.all(Radius.circular(20)),
                                        borderSide: BorderSide(
                                            color: Colors.red
                                        )
                                    )
                                ),
                                keyboardType: TextInputType.number,
                              ),
                            ),
                          ],
                        );
                      }else{
                        return CircularProgressIndicator();
                      }
                    },
                ),
                const SizedBox(height: 30,),
                Container(
                  height: 50,
                  width: 120,
                  child: ElevatedButton(
                  style: ButtonStyle(
                    backgroundColor: WidgetStatePropertyAll(
                      Colors.red
                    ),
                    shape: WidgetStatePropertyAll(
                      RoundedRectangleBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10))
                      ),
                    )
                  )
                  ,onPressed: (){}, child: Text("Güncelle" , style: TextStyle(color:  Colors.white , fontWeight: FontWeight.bold),)),
                )
            ],
          ),
        ),
      ),
    );
  }
}
