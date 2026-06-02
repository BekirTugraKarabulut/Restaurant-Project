import 'dart:convert';
import 'package:http/http.dart' as http;

class GetOrdersByUsername {
  Future<List<Map<String, dynamic>>> getOrdersByUsername(String username) async {
    final url = Uri.parse("http://10.0.2.2:8090/pay/list/$username");

    final response = await http.get(
      url,
      headers: {
        "Content-Type": "application/json",
      },
    );

    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);

      return data.map((order) {
        return {
          "payId": order["payId"],
          "address": order["address"],
          "price": order["price"],
        };
      }).toList();
    } else {
      throw Exception("Failed to load orders");
    }
  }
}