import 'package:http/http.dart' as http;

class GetNameService {

  Future<String?> getName(String username) async {

    final url = Uri.parse("http://10.0.2.2:8090/customer/name/${username}");

    final response = await http.get(

      url,
      headers: {
        "Content-Type": "application/json",
      }

    );

    if(response.statusCode == 200){
      return response.body;
    } else {
      throw Exception("Kullanıcı bulunamadı");
    }

  }

}