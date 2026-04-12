import 'dart:convert';

import 'package:http/http.dart' as http;

class FavoritesService {
  
    Future<List<Map<String , dynamic>>> favorites() async{

      final url = Uri.parse("http://10.0.2.2:8090/favorites");

      final response = await http.get(

        url,
        headers: {
          "Content-Type": "application/json",
        }
      );

      if(response.statusCode == 200){
        final List<dynamic> data = response.body.isNotEmpty ? List<Map<String, dynamic>>.from(jsonDecode(response.body)) : [];
        return data.map((item) => Map<String, dynamic>.from(item)).toList();
      }else {
        throw Exception("Favoriler alınırken bir hata oluştu");
      }

    }
  
}