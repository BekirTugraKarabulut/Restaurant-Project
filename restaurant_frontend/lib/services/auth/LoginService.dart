import 'dart:convert';

import 'package:http/http.dart' as http;

class LoginService {

        Future<bool> login(String username , String password) async {

          final url = Uri.parse("http://10.0.2.2:8090/auth/login");

          final response = await http.post(

            url,
            headers: {
              "Content-Type": "application/json"
            },
            body: jsonEncode({
              "username": username,
              "password": password
            })

          );

          if(response.statusCode == 200){
            return true;
          }else {
            return false;
          }
        }

}