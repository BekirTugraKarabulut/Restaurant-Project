import 'dart:convert';

import 'package:http/http.dart' as http;

class RegisterService {

    Future<bool> register(String username , String name , String password) async {

      final url = Uri.parse("http://10.0.2.2:8090/auth/register");

      final response = await http.post(
        url,
        headers: {
          "Content-Type": "application/json"
        },
        body: jsonEncode({
          "username": username,
          "name": name,
          "password": password
        })
      );

      if(response.statusCode == 200){
        return true;
      }else{
        throw Exception("Failed to register");
      }
    }
}