import 'package:flutter/material.dart';
import 'package:restaurant_frontend/pages/navigation_page/CartPage.dart';
import 'package:restaurant_frontend/pages/navigation_page/CategoriesPage.dart';
import 'package:restaurant_frontend/pages/navigation_page/ProfilePage.dart';
import 'package:restaurant_frontend/services/customer/GetNameService.dart';
import 'package:restaurant_frontend/services/favorites/FavoritesService.dart';

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

  Widget homeContent() {
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        child: Container(
          decoration: BoxDecoration(
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
              Row(
                children: const [
                  Icon(Icons.location_on),
                  SizedBox(width: 5),
                  Text("Adres Ekleyiniz"),
                ],
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
                                  child: Row(mainAxisAlignment: MainAxisAlignment.spaceAround,
                                    children: [
                                      Image.asset("images/${favorite["imageUrl"]}" , width: 100 , height: 100,),
                                      Text(favorite["name"] , style: TextStyle(color: Colors.black, fontWeight: FontWeight.bold),),
                                      Text(favorite["price"].toString() + " ₺" , style: TextStyle(color: Colors.red , fontWeight: FontWeight.bold),),
                                    ],
                                  ),
                                ),
                              ),
                            );
                          },
                      );
                    }else{
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
      Profilepage(username: widget.username),
    ];

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.red,
        title: const Text(
          "Anasayfa",
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
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
            icon: Icon(Icons.person),
            label: "Profil",
          ),
        ],
      ),
    );
  }
}