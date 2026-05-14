import 'dart:convert';
import 'package:http/http.dart' as http;

class FoodService {

  Future<List<Map<String, dynamic>>> allFoods() async {
    final url = Uri.parse("http://10.0.2.2:8090/admin/get/foods");

    final response = await http.get(
      url,
      headers: {
        "Content-Type": "application/json",
      },
    );

    if (response.statusCode == 200) {

      return List<Map<String , dynamic>>.from(jsonDecode(response.body));

    } else {
      throw Exception("Failed to load foods");
    }
  }
}