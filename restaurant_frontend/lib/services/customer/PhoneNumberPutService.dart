import 'dart:convert';

import 'package:http/http.dart' as http;

class PhoneNumberPutService {

    Future<Map<String , dynamic>> putPhoneNumber(String username , String phoneNumber) async {
      
      final url = Uri.parse("http://10.0.2.2:8090/customer/profile/phoneNumber/${username}");

      final response = await http.put(

        url,
        headers: {
          "Content-Type": "application/json"
        },
        body: jsonEncode(phoneNumber)
      );

      if(response.statusCode == 200){
        return jsonDecode(response.body);
      }else{
        throw Exception("Failed to update phone number");
      }

    }

}