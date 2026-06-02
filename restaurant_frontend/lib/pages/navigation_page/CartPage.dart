import 'package:flutter/material.dart';
import 'package:restaurant_frontend/pages/process/OrderPage.dart';
import 'package:restaurant_frontend/services/cart/CartService.dart';

class Cartpage extends StatefulWidget {
  final String username;

  const Cartpage({super.key, required this.username});

  @override
  State<Cartpage> createState() => _CartpageState();
}

class _CartpageState extends State<Cartpage> {
  final CartService cartService = CartService();

  late Future<List<Map<String, dynamic>>> cartsFuture;
  late Future<int> totalPriceFuture;

  @override
  void initState() {
    super.initState();
    cartsFuture = cartService.getCartsByUsername(widget.username);
    totalPriceFuture = cartService.getTotalPrice(widget.username);
  }

  void refreshCart() {
    setState(() {
      cartsFuture = cartService.getCartsByUsername(widget.username);
      totalPriceFuture = cartService.getTotalPrice(widget.username);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      body: Column(
        children: [
          Expanded(
            child: FutureBuilder<List<Map<String, dynamic>>>(
              future: cartsFuture,
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const Center(child: CircularProgressIndicator());
                }

                if (snapshot.hasError) {
                  return Center(child: Text("Hata: ${snapshot.error}"));
                }

                if (!snapshot.hasData || snapshot.data!.isEmpty) {
                  return Center(
                    child: Row(mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text("Sepet Boş! " , style: TextStyle(color:  Colors.black , fontWeight: FontWeight.bold  , fontSize: 20),),
                        Icon(Icons.search , color: Colors.black, size: 30,)

                      ],
                    ),
                  );
                }

                final carts = snapshot.data!;

                return ListView.builder(
                  itemCount: carts.length,
                  itemBuilder: (context, index) {
                    final cart = carts[index];

                    return Card(
                      margin: const EdgeInsets.symmetric(
                        horizontal: 12,
                        vertical: 8,
                      ),
                      child: ListTile(
                        leading: SizedBox(
                            width: 60,
                            height: 60,
                            child: Image.asset("images/${cart["imageUrl"]}" ,
                              fit: BoxFit.cover,),),
                        title: Text(
                          cart["productName"].toString(),
                          style: const TextStyle(
                            fontWeight: FontWeight.bold,
                            color: Colors.black
                          ),
                        ),
                        subtitle: Text(
                          "${cart["price"]} TL",
                          style: const TextStyle(
                            fontWeight: FontWeight.bold,
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                        trailing: IconButton(onPressed: () async {

                          bool result = await cartService.deleteCart(cart["cartId"]);

                          if(result){
                            ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(content: Text("Ürün sepetten silindi"))
                            );
                            refreshCart();
                          }else{
                            ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(content: Text("Ürün sepetten silinirken bir hata oluştu"))
                            );
                          }
                        },
                          icon: Icon(Icons.delete_forever, color: Colors.red,),
                      ),
                      ),
                    );
                  },
                );
              },
            ),
          ),

          Padding(
            padding: const EdgeInsets.only(bottom: 25),
            child: Row(mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Container(
                  padding: const EdgeInsets.all(15),
                  width: 170,
                  height: 55,
                  decoration: BoxDecoration(
                      color: Colors.black,
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(20),
                      bottomLeft: Radius.circular(20)
                    )
                  ),
                  child: FutureBuilder<int>(
                    future: totalPriceFuture,
                    builder: (context, snapshot) {
                      final total = snapshot.data ?? 0;
                      return Text(
                        "Sepet : $total TL",
                        style: const TextStyle(
                          fontSize: 18,
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                        ),
                      );
                    },
                  ),
                ),
                Container(
                  width: 180,
                  height: 55,
                  child: ElevatedButton(
                  style: ButtonStyle(
                    backgroundColor: WidgetStatePropertyAll(
                      Colors.red
                    ),
                    shape: WidgetStatePropertyAll(
                      RoundedRectangleBorder(
                        borderRadius: BorderRadius.only(
                          topRight: Radius.circular(20),
                          bottomRight: Radius.circular(20)
                        )
                      )
                    ),
                  )
                  ,onPressed: (){
                      Navigator.push(context, MaterialPageRoute(builder: (context) => Orderpage(username: widget.username)));
                  }, child: Text("Siparişi Onayla" , style: TextStyle(
                      color: Colors.white ,
                      fontWeight: FontWeight.bold,
                      fontSize: 18,
                  ),)),
                )
              ],
            ),
          ),

        ],
      ),
    );
  }
}