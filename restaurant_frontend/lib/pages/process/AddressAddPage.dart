import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:restaurant_frontend/services/address/AddressAddService.dart';

class Addressaddpage extends StatefulWidget {

  final String username;

  const Addressaddpage({super.key, required this.username});

  @override
  State<Addressaddpage> createState() => _AddressaddpageState();
}

class _AddressaddpageState extends State<Addressaddpage> {

  final MapController mapController = MapController();
  LatLng selectedLocation = LatLng(38.3552, 38.3095);
  final AddressAddService addressAddService = AddressAddService();
  var addressTitleController = TextEditingController();
  var streetAddressController = TextEditingController();

  @override
  Widget build(BuildContext context) {

    return Scaffold(

      appBar: AppBar(
        backgroundColor: Colors.red,
        title: const Text(
          "Adres Ekle",
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
        ),
        centerTitle: true,
        leading: IconButton(
          onPressed: (){
            Navigator.pop(context);
          },
          icon: const Icon(Icons.arrow_back_ios_sharp, color: Colors.white),
        ),
      ),

      body: SingleChildScrollView(
        child: Column(
          children: [
            SizedBox(
              height: 300,
              child: FlutterMap(
                mapController: mapController,
                options: MapOptions(
                  initialCenter: selectedLocation,
                  initialZoom: 13,
                  interactionOptions: const InteractionOptions(
                    flags: InteractiveFlag.all,
                  ),
                  onTap: (TapPosition tapPosition, LatLng point) {
                    setState(() {
                      selectedLocation = point;
                    });
                  },
                ),
                children: [
                  TileLayer(
                    urlTemplate: "https://tile.openstreetmap.org/{z}/{x}/{y}.png",
                    userAgentPackageName: "com.example.restaurant_frontend"
                  ),
                  MarkerLayer(
                    markers: [
                      Marker(
                        point: selectedLocation,
                        width: 80,
                        height: 80,
                        child: const Icon(
                          Icons.location_pin,
                          size: 40,
                          color: Colors.red,
                        ),
                      )
                    ],
                  )
                ],
              ),
            ),
            const SizedBox(height: 20),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: Column(
                children: [
                  TextField(
                    controller: addressTitleController,
                    decoration: InputDecoration(
                      labelText: "Adres Başlığı",
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(12),
                      ),
                    ),
                  ),
                  const SizedBox(height: 10),
                  TextField(
                    controller: streetAddressController,
                    decoration: InputDecoration(
                      labelText: "Açık Adres",
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(12),
                      ),
                    ),
                    maxLines: 3,
                  ),
                  const SizedBox(height: 20),
                  SizedBox(
                    width: double.infinity,
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.red,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                      ),
                      onPressed: () async {
                        print("Lat: ${selectedLocation.latitude}");
                        print("Lng: ${selectedLocation.longitude}");

                        bool response = await addressAddService.addAddress(widget.username, streetAddressController.text, addressTitleController.text);
                        if(response){
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(content: Text("Adres Kaydedildi."),
                            action: SnackBarAction(label: "Tamam", onPressed: (){}),
                            )
                          );
                        }else{
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(content: Text("Adres Kaydedilemedi."),
                            action: SnackBarAction(label: "Tamam", onPressed: (){}),
                            )
                          );
                          return;
                        }
                      },
                      child: const Text(
                        "Adresi Kaydet",
                        style: TextStyle(color: Colors.white),
                      ),
                    ),
                  )
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}