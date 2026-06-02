import 'dart:convert';
import 'package:http/http.dart' as http;

class CourierService {
  Future<int?> getCourierTime(String username) async {
    final response = await http.get(
      Uri.parse("http://10.0.2.2:8090/courier/$username"),
    );

    if (response.statusCode == 200) {
      final data = jsonDecode(response.body);
      return data["time"];
    }

    return null;
  }
}