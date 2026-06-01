import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';
import 'package:restaurant_frontend/pages/home/home_page.dart';
import 'package:restaurant_frontend/pages/process/PreparePage.dart';
import 'package:restaurant_frontend/services/pay/PayService.dart';

class Creditcartprocesspage extends StatefulWidget {
  
  String username;
  Creditcartprocesspage({
    super.key,
    required this.username,
  });

  @override
  State<Creditcartprocesspage> createState() => _CreditcartprocesspageState();
}

class _CreditcartprocesspageState extends State<Creditcartprocesspage> {

  var creditCart = TextEditingController();
  var mmyy = TextEditingController();
  var cvv = TextEditingController();
  final PayService payService = PayService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.red,
        centerTitle: true,
        title: const Text(
          "Ödeme Ekranı",
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
        ),
        leading: IconButton(
          onPressed: () {
            Navigator.pop(context);
          },
          icon: const Icon(
            Icons.arrow_back_ios_outlined,
            color: Colors.white,
          ),
        ),
      ),
      body: SingleChildScrollView(
        child: Center(
          child: Column(
            children: [
              Lottie.asset("asset/credit_cart.json" , width: 200 , height: 180),
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(top: 12),
                      child: TextField(
                        controller: creditCart,
                        keyboardType: TextInputType.number,
                        style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),
                        decoration: InputDecoration(
                          suffixIcon: Icon(Icons.credit_card, color: Colors.black,),
                          hintStyle: TextStyle(color: Colors.black ),
                          hintText: "Kredi Kart No",
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
                        controller: mmyy,
                        keyboardType: TextInputType.number,
                        style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),
                        decoration: InputDecoration(
                          suffixIcon: Icon(Icons.date_range, color: Colors.black,),
                          hintStyle: TextStyle(color: Colors.black ),
                          hintText: "mm/yy",
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
                        controller: cvv,
                        keyboardType: TextInputType.number,
                        style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),
                        decoration: InputDecoration(
                          suffixIcon: Icon(Icons.security, color: Colors.black,),
                          hintStyle: TextStyle(color: Colors.black ),
                          hintText: "CVV",
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
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(top: 20),
                child: ElevatedButton(style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(Colors.red),
                  padding: MaterialStateProperty.all(EdgeInsets.symmetric(horizontal: 40 , vertical: 12)),
                  shape: MaterialStateProperty.all(RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)))
                ) , onPressed: () async {

                  bool result = await payService.payByUsername(widget.username, creditCart.text, mmyy.text, cvv.text);

                  if(result){
                    ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text("Ödeme Başarılı")));
                    Navigator.push(context, MaterialPageRoute(builder: (context) => Preparepage(username: widget.username)));
                  }else{
                    ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text("Ödeme Başarısız"),
                        action: SnackBarAction(label: "Tamam", onPressed: (){}),
                        )
                    );
                    return;
                  }

                }, child: Text("Ödeme Yap" , style: TextStyle(color: Colors.white , fontWeight: FontWeight.bold),)),
              ),

            ],
          ),
        ),
      ),
    );
  }
}
