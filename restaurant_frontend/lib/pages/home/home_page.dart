import 'package:flutter/material.dart';
import 'package:restaurant_frontend/pages/auth/login.dart';
import 'package:restaurant_frontend/pages/navigation_page/AboutUsPage.dart';
import 'package:restaurant_frontend/pages/navigation_page/CartPage.dart';
import 'package:restaurant_frontend/pages/navigation_page/CategoriesPage.dart';
import 'package:restaurant_frontend/pages/process/AddressAddPage.dart';
import 'package:restaurant_frontend/services/customer/GetNameService.dart';
import 'package:restaurant_frontend/services/favorites/FavoritesService.dart';
import 'package:restaurant_frontend/services/address/AddressNameService.dart';

class HomePage extends StatefulWidget {

  final String username;
  const HomePage({super.key, required this.username});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  int selectedIndex = 0;

  final images = [
    "images/pizza.jpeg",
    "images/hamburger.jpeg",
    "images/san-sebastian.jpeg",
  ];

  final GetNameService getNameService = GetNameService();
  final FavoritesService favoritesService = FavoritesService();
  final AddressNameService addressNameService = AddressNameService();

  Widget homeContent() {
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        child: Container(
          decoration: const BoxDecoration(
              color: Colors.white
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [

              const SizedBox(height: 20),

              FutureBuilder<String?>(
                future: getNameService.getName(widget.username),
                builder: (context, snapshot) {

                  if (snapshot.connectionState == ConnectionState.waiting) {
                    return const Center(child: CircularProgressIndicator());
                  }

                  if (snapshot.hasError) {
                    return const Text("Kullanıcı bulunamadı");
                  }

                  return Text(
                    "Hoş Geldiniz, ${snapshot.data}",
                    style: const TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                    ),
                  );
                },
              ),
              const SizedBox(height: 10),
              FutureBuilder<String?>(
                future: addressNameService.getAddressName(widget.username),
                builder: (context, snapshot) {
                  String addressText = "Adres Ekleyiniz";
                  if(snapshot.hasData && snapshot.data!.isNotEmpty){
                    addressText = snapshot.data!;
                  }
                  return Row(
                    children: [
                      const Icon(Icons.location_on),
                      const SizedBox(width: 5),
                      GestureDetector(
                        onTap: (){
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => Addressaddpage(username: widget.username),
                            ),
                          ).then((value){
                            setState(() {});
                          });
                        },
                        child: Text(
                          addressText,
                          style: const TextStyle(
                            fontWeight: FontWeight.bold,
                            color: Colors.black,
                            decoration: TextDecoration.underline,
                          ),
                        ),
                      ),
                    ],
                  );
                },
              ),
              const SizedBox(height: 20),
              SizedBox(
                height: 200,
                child: PageView.builder(
                  itemCount: images.length,
                  itemBuilder: (context, index) {
                    return Container(
                      margin: const EdgeInsets.all(5),
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(20),
                        image: DecorationImage(
                          image: AssetImage(images[index]),
                          fit: BoxFit.cover,
                        ),
                      ),
                    );
                  },
                ),
              ),
              const SizedBox(height: 20),
              const Text(
                "En Çok Tercih Edilenler",
                style: TextStyle(
                  color: Colors.black,
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(height: 5),
              FutureBuilder(
                future: favoritesService.favorites(),
                builder: (context, snapshot) {
                  if(snapshot.hasData){
                    var favorites = snapshot.data!;
                    return ListView.builder(
                      itemCount: favorites.length,
                      shrinkWrap: true,
                      physics: const NeverScrollableScrollPhysics(),
                      itemBuilder: (context, index) {
                        var favorite = favorites[index];
                        return Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Container(
                            decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(10),
                                color: Colors.black
                            ),
                            child: Card(
                              color: Colors.white,
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.spaceAround,
                                children: [
                                  Image.asset(
                                    "images/${favorite["imageUrl"]}",
                                    width: 100,
                                    height: 100,
                                  ),
                                  Column(
                                    children: [
                                      Text(
                                        favorite["name"],
                                        style: const TextStyle(
                                            color: Colors.black,
                                            fontWeight: FontWeight.bold
                                        ),
                                      ),
                                      Text(
                                        "${favorite["price"]} ₺",
                                        style: const TextStyle(
                                            color: Colors.red,
                                            fontWeight: FontWeight.bold
                                        ),
                                      ),
                                    ],
                                  ),
                                  ElevatedButton(
                                    style: ButtonStyle(
                                        shape: WidgetStatePropertyAll(
                                          RoundedRectangleBorder(
                                            borderRadius: BorderRadius.circular(10),
                                          ),
                                        ),
                                        backgroundColor: const WidgetStatePropertyAll(
                                          Colors.red,
                                        )
                                    ),
                                    onPressed: (){},
                                    child: const Text(
                                      "Ekle",
                                      style: TextStyle(color: Colors.white),
                                    ),
                                  )
                                ],
                              ),
                            ),
                          ),
                        );
                      },
                    );
                  } else {
                    return const Center(child: CircularProgressIndicator());
                  }
                },
              )
            ],
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {

    final pages = [
      homeContent(),
      Categoriespage(username: widget.username),
      Cartpage(username: widget.username),
      Aboutuspage(username: widget.username),
    ];

    return Scaffold(
      appBar: AppBar(
        leading: IconButton(onPressed: (){
            Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => Login()));
        }, icon:  Icon(Icons.login)),
        backgroundColor: Colors.red,
        title: const Text(
          "Tastyra",
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.normal),
        ),
        centerTitle: true,
        actions: [
          Padding(
            padding: const EdgeInsets.only(right: 10),
            child: SizedBox(
              height: 40,
              child: ClipRRect(
                borderRadius: BorderRadius.circular(20),
                child: Image.asset("images/account.jpeg"),
              ),
            ),
          )
        ],
      ),

      body: pages[selectedIndex],

      bottomNavigationBar: BottomNavigationBar(
        currentIndex: selectedIndex,
        selectedItemColor: Colors.red,
        type: BottomNavigationBarType.fixed,
        unselectedItemColor: Colors.grey,
        onTap: (index) {
          setState(() {
            selectedIndex = index;
          });
        },
        items: const [

          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: "Anasayfa",
          ),

          BottomNavigationBarItem(
            icon: Icon(Icons.fastfood),
            label: "Ürünler",
          ),

          BottomNavigationBarItem(
            icon: Icon(Icons.shopping_cart),
            label: "Sepet",
          ),

          BottomNavigationBarItem(
            icon: Icon(Icons.account_balance_sharp),
            label: "Hakkımızda",
          ),

        ],
      ),
    );
  }
}