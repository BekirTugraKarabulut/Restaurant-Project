import 'package:flutter/material.dart';
import 'package:restaurant_frontend/services/admin/DessertsService.dart';
import 'package:restaurant_frontend/services/admin/DrinksService.dart';
import 'package:restaurant_frontend/services/admin/FoodsService.dart';
import 'package:restaurant_frontend/services/admin/SnacksService.dart';


class Categoriespage extends StatefulWidget {
  final username;

  const Categoriespage({super.key, this.username});

  @override
  State<Categoriespage> createState() => _CategoriespageState();
}

class _CategoriespageState extends State<Categoriespage> {
  final FoodService foodService = FoodService();
  final DessertsService dessertService = DessertsService();
  final DrinksService drinkService = DrinksService();
  final SnacksService snackService = SnacksService();

  final List<String> categories = [
    "Yemekler",
    "Tatlılar",
    "İçecekler",
    "Atıştırmalık"
  ];

  int selectedIndex = 0;

  final List<String> images = [
    "https://cdn-icons-png.flaticon.com/512/3075/3075977.png",
    "https://cdn-icons-png.flaticon.com/512/992/992754.png",
    "https://cdn-icons-png.flaticon.com/512/2405/2405479.png",
    "https://cdn-icons-png.flaticon.com/512/1046/1046786.png"
  ];

  Future<List<Map<String, dynamic>>> getSelectedCategoryItems() {
    if (selectedIndex == 0) {
      return foodService.allFoods();
    } else if (selectedIndex == 1) {
      return dessertService.allDesserts();
    } else if (selectedIndex == 2) {
      return drinkService.allDrinks();
    } else {
      return snackService.allSnacks();
    }
  }

  String getItemName(Map<String, dynamic> item) {
    if (selectedIndex == 0) {
      return item["foodName"].toString();
    } else if (selectedIndex == 1) {
      return item["dessertName"].toString();
    } else if (selectedIndex == 2) {
      return item["drinkName"].toString();
    } else {
      return item["snackName"].toString();
    }
  }

  String getItemPrice(Map<String, dynamic> item) {
    if (selectedIndex == 0) {
      return item["price"].toString();
    } else if (selectedIndex == 1) {
      return item["price"].toString();
    } else if (selectedIndex == 2) {
      return item["price"].toString();
    } else {
      return item["price"].toString();
    }
  }

  String? getItemImage(Map<String, dynamic> item) {
    if (selectedIndex == 0) {
      return item["foodImage"];
    } else if (selectedIndex == 1) {
      return item["dessertImage"];
    } else if (selectedIndex == 2) {
      return item["drinkImage"];
    } else {
      return item["snackImage"];
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          const SizedBox(height: 20),

          SizedBox(
            height: 120,
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: categories.length,
              itemBuilder: (context, index) {
                bool isSelected = selectedIndex == index;

                return GestureDetector(
                  onTap: () {
                    setState(() {
                      selectedIndex = index;
                    });
                  },
                  child: Container(
                    width: 90,
                    margin: const EdgeInsets.all(8),
                    child: Column(
                      children: [
                        Container(
                          decoration: BoxDecoration(
                            color: isSelected ? Colors.red : Colors.grey[200],
                            borderRadius: BorderRadius.circular(15),
                          ),
                          padding: const EdgeInsets.all(10),
                          child: Image.network(
                            images[index],
                            height: 40,
                            width: 40,
                          ),
                        ),
                        const SizedBox(height: 6),
                        Text(
                          categories[index],
                          style: TextStyle(
                            color: isSelected ? Colors.red : Colors.black,
                            fontWeight: FontWeight.bold,
                          ),
                        )
                      ],
                    ),
                  ),
                );
              },
            ),
          ),

          const Divider(),

          Expanded(
            child: FutureBuilder<List<Map<String, dynamic>>>(
              future: getSelectedCategoryItems(),
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                }

                if (snapshot.hasError) {
                  return Center(
                    child: Text("Hata: ${snapshot.error}"),
                  );
                }

                if (!snapshot.hasData || snapshot.data!.isEmpty) {
                  return Center(
                    child: Text("${categories[selectedIndex]} bulunamadı"),
                  );
                }

                final items = snapshot.data!;

                return ListView.builder(
                  itemCount: items.length,
                  itemBuilder: (context, index) {
                    final item = items[index];
                    final imageUrl = getItemImage(item);

                    return Card(

                      margin: const EdgeInsets.symmetric(
                        horizontal: 12,
                        vertical: 8,
                      ),
                      child:
                      ListTile(
                        leading: imageUrl != null && imageUrl.isNotEmpty
                            ? Image.network(
                          imageUrl,
                          width: 55,
                          height: 55,
                          fit: BoxFit.cover,
                        )
                            : const Icon(Icons.fastfood),
                        title: Text(
                          getItemName(item),
                          style: const TextStyle(
                            fontWeight: FontWeight.bold,
                          ),
                        ),

                        trailing: ElevatedButton(
                        style: ButtonStyle(
                          backgroundColor: WidgetStatePropertyAll(
                            Colors.red
                          ),
                          shape: WidgetStatePropertyAll(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(12),
                            )
                          )
                        ), onPressed: (){

                        }, child: Text("Sepete Ekle" , style: TextStyle(color: Colors.white),)),
                        subtitle: Text("${getItemPrice(item)} TL"),
                      ),
                    );
                  },
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}