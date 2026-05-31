import 'dart:convert';
import 'package:http/http.dart' as http;

class CartService {

  final String baseUrl = "http://10.0.2.2:8090";

  Future<bool> addToCart({
    required String username,
    required int productId,
    required String productType,
  }) async {
    final response = await http.post(
      Uri.parse("$baseUrl/cart/saveCart"),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({
        "username": username,
        "productId": productId,
        "productType": productType,
      }),
    );

    return response.statusCode == 200;
  }

  Future<List<Map<String, dynamic>>> getCartsByUsername(String username) async {
    final response = await http.get(
      Uri.parse("$baseUrl/cart/getCartsByUsername/$username"),
    );

    if (response.statusCode == 200) {
      return List<Map<String, dynamic>>.from(jsonDecode(response.body));
    }

    return [];
  }

  Future<int> getTotalPrice(String username) async {
    final response = await http.get(
      Uri.parse("$baseUrl/cart/calculateTotalPrice/$username"),
    );

    if (response.statusCode == 200) {
      return int.parse(response.body.toString());
    }

    return 0;
  }

  Future<bool> deleteCart(int cartId) async{

    final response = await http.delete(
      Uri.parse("$baseUrl/cart/deleteCartByCartId/$cartId"),
    );

    if(response.statusCode == 200){
      return true;
    }else{
      return false;
    }

  }

}