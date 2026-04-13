import 'dart:convert';

import 'package:http/http.dart' as http;

class GetCustomerInfo{

    Future<Map<String , dynamic>> getCustomerInfo(String username) async {
      
      final url = Uri.parse("http://10.0.2.2:8090/customer/profile/${username}");
      
      final response = await http.get(

        url,
        headers: {
          "Content-Type": "application/json",
        },
      );

      if(response.statusCode == 200) {

        final data = jsonDecode(response.body);
        return data;

      } else {
        throw Exception("Failed to load customer info");
      }
    }

}