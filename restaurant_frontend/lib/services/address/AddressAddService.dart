import 'dart:convert';

import 'package:http/http.dart' as http;

class AddressAddService {

    Future<bool> addAddress(String username , String street , String addressTitle) async {
      
      final url = Uri.parse("http://10.0.2.2:8090/saveAddress");

      final response = await http.post(

        url,
        headers: {
          "Content-Type": "application/json",
        },
        body: jsonEncode({
          "dtoCustomer" : {
            "username": username,
          },
          "street": street,
          "addressTitle": addressTitle,
        })
      );

      if(response.statusCode == 200) {
        return true;
      } else {
        return false;
      }
    }

}