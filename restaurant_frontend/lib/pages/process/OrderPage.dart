import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:restaurant_frontend/pages/process/CreditCartProcessPage.dart';
import 'package:restaurant_frontend/services/address/AddressGetByUsernameService.dart';
import 'package:restaurant_frontend/services/cart/CartService.dart';

class Orderpage extends StatefulWidget {
  final String username;

  const Orderpage({
    super.key,
    required this.username,
  });

  @override
  State<Orderpage> createState() => _OrderpageState();
}

class _OrderpageState extends State<Orderpage> {

  final AddressGetByUsernameService addressGetByUsernameService = AddressGetByUsernameService();
  final CartService cartService = CartService();
  
  final LatLng restaurantLocation = const LatLng(38.3500, 38.3050);
  final LatLng customerLocation = const LatLng(38.3552, 38.3095);

  var coupenCode = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.red,
        centerTitle: true,
        title: const Text(
          "Sipariş Ver",
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
        ),
        leading: IconButton(
          onPressed: () {
            Navigator.pop(context);
          },
          icon: const Icon(
            Icons.arrow_back_ios_outlined,
            color: Colors.white,
          ),
        ),
      ),

      body: SingleChildScrollView(
        child: Column(
          children: [
            SizedBox(
              height: 150,
              child: FlutterMap(
                options: MapOptions(
                  initialCenter: customerLocation,
                  initialZoom: 15,
                ),
                children: [
                  TileLayer(
                    urlTemplate:
                    "https://tile.openstreetmap.org/{z}/{x}/{y}.png",
                    userAgentPackageName: "com.example.restaurant_frontend",
                  ),

                  MarkerLayer(
                    markers: [
                      Marker(
                        point: restaurantLocation,
                        width: 50,
                        height: 50,
                        child: const Icon(
                          Icons.restaurant,
                          color: Colors.green,
                          size: 40,
                        ),
                      ),

                      Marker(
                        point: customerLocation,
                        width: 50,
                        height: 50,
                        child: const Icon(
                          Icons.home,
                          color: Colors.red,
                          size: 40,
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
            const SizedBox(height: 20),

            FutureBuilder(
                future: addressGetByUsernameService.getAddressByUsername(widget.username),
                builder: (context, snapshot) {
                  if(snapshot.hasData){
                    var address = snapshot.data;
                    return Row(mainAxisAlignment: MainAxisAlignment.spaceAround,
                      children: [
                        Text("Adresiniz : ${address}", style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),),
                        Icon(Icons.location_on , color: Colors.black , size: 20,)
                      ],
                    );
                  }else{
                    return const CircularProgressIndicator();
                  }
                },
            ),
            Padding(
              padding: const EdgeInsets.only(top: 20,),
              child: Column(
                children: [
                  FutureBuilder<List<Map<String, dynamic>>>(
                      future: cartService.getCartsByUsername(widget.username),
                      builder: (context, snapshot) {
                        if(snapshot.hasData){
                          var carts = snapshot.data!;
                          return ListView.builder(
                              shrinkWrap: true,
                              physics: NeverScrollableScrollPhysics(),
                              itemCount: carts.length,
                              itemBuilder: (context, index) {
                                var cart = carts[index];
                                return Card(
                                  margin: const EdgeInsets.symmetric(horizontal: 12, vertical: 8),
                                  child: ListTile(
                                    leading: SizedBox(
                                        width: 60,
                                        height: 60,
                                        child: Image.asset("images/${cart["imageUrl"]}" , fit: BoxFit.cover,)),
                                    title: Text("${cart["productName"]}", style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold , fontSize: 15),),
                                    trailing: Text("${cart["price"]} TL" , style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold , fontSize: 15),),
                                  ),
                                );
                              },
                          );
                        }else{
                          return const CircularProgressIndicator();
                        }
                      },
                  ),
                  Padding(
                    padding: const EdgeInsets.only(right: 25),
                    child: Row(mainAxisAlignment: MainAxisAlignment.end,
                      children: [
                        FutureBuilder<int>(
                          future: cartService.getTotalPrice(widget.username),
                          builder: (context, snapshot) {
                            if(snapshot.hasData){
                              var totalPrice = snapshot.data!;
                              return Text("Toplam Tutar : ${totalPrice} TL" , style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold , fontSize: 16),);
                            }else{
                              return const CircularProgressIndicator();
                            }
                          },
                        )
                      ],
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 25 , left: 10 , right: 10),
                    child: Row(mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: [
                        SizedBox(
                          height: 50,
                          width: 150,
                          child: Expanded(
                            child: TextField(
                              controller: coupenCode,
                              decoration: InputDecoration(
                                hintStyle: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),
                                hintText: "Kupon Kodu",
                                enabledBorder: OutlineInputBorder(
                                  borderSide: BorderSide(color: Colors.black),
                                  borderRadius: BorderRadius.circular(8),
                                ),
                                focusedBorder: OutlineInputBorder(
                                  borderSide: BorderSide(color: Colors.black),
                                  borderRadius: BorderRadius.circular(8),
                                ),
                                disabledBorder: OutlineInputBorder(
                                  borderSide: BorderSide(color: Colors.black),
                                  borderRadius: BorderRadius.circular(8),
                                )
                              ),
                            ),
                          ),
                        ),
                        ElevatedButton(
                        style: ButtonStyle(
                          backgroundColor: MaterialStateProperty.all(Colors.green),
                          padding: MaterialStateProperty.all(const EdgeInsets.symmetric(horizontal: 20, vertical: 12)),
                          shape: MaterialStateProperty.all(RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(8),
                          )),
                        ),onPressed: (){

                        }, child: Text("Kupon Ekle" , style: TextStyle(color: Colors.white , fontWeight: FontWeight.bold),))
                      ],
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 45),
                    child: Row(mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        ElevatedButton(style: ButtonStyle(
                          backgroundColor: MaterialStateProperty.all(Colors.red),
                          padding: MaterialStateProperty.all(const EdgeInsets.symmetric(horizontal: 40, vertical: 15)),
                          shape: MaterialStateProperty.all(RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(8),
                          )),
                        ) , onPressed: (){
                            Navigator.push(context, MaterialPageRoute(builder: (context) => Creditcartprocesspage(username: widget.username)));
                        }, child: Text("Satın Al" , style: TextStyle(color: Colors.white , fontWeight: FontWeight.bold),))
                      ],
                    ),
                  ),

                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

}