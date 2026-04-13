import 'package:flutter/material.dart';
import 'package:restaurant_frontend/services/address/AddressGetByUsernameService.dart';

class Customeraddresspage extends StatefulWidget {

  final username;
  const Customeraddresspage({super.key, this.username});

  @override
  State<Customeraddresspage> createState() => _CustomeraddresspageState();
}

class _CustomeraddresspageState extends State<Customeraddresspage> {
  
  final AddressGetByUsernameService addressGetByUsernameService = AddressGetByUsernameService();
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        leading: IconButton(onPressed: (){
          Navigator.pop(context);
        }, icon: Icon(Icons.arrow_back_ios_new , color:  Colors.white,)),
        backgroundColor: Colors.red,
        centerTitle: true,
        title: Text("Adreslerim" , style: TextStyle(color: Colors.white , fontWeight: FontWeight.normal),),
      ),
      body: Center(
        child: Column(
          children: [
            FutureBuilder<String?>(
                future: addressGetByUsernameService.getAddressByUsername(widget.username),
                builder: (context, snapshot) {
                  if(snapshot.hasData){
                    var address = snapshot.data!;
                    return Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Container(
                        decoration: BoxDecoration(
                          color: Colors.red,
                          borderRadius: BorderRadius.all(Radius.circular(20))
                        ),
                        height: 100,
                        child: Card(
                          color: Colors.white,
                          child: Row(mainAxisAlignment: MainAxisAlignment.spaceAround,
                            children: [
                              Icon(Icons.location_on , color: Colors.red,),
                              Text(address , style: TextStyle(fontSize: 16 , color: Colors.black , fontWeight: FontWeight.bold),),
                              IconButton(onPressed: (){}, icon: Icon(Icons.clear , color: Colors.black,))
                            ],
                          ),
                        ),
                      ),
                    );
                  }else{
                    return CircularProgressIndicator();
                  }
                },
            )
          ],
        ),
      ),
    );
  }
}
