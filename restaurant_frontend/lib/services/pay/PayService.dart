import 'dart:convert';

import 'package:http/http.dart' as http;

class PayService{

  Future<bool> payByUsername(String username , String creditCart , String mmyy , String cvv) async {

    final url = Uri.parse("http://10.0.2.2:8090/pay/$username");

    final response = await http.post(

      url,
      headers: {
        "Content-Type": "application/json"
      },
      body: jsonEncode(
        {
          "cardNumber": creditCart,
          "mmyy": mmyy,
          "cvv": cvv
        }
      )
    );

    if(response.statusCode == 200){
      return true;
    }else{
      return false;
    }
  }

}
