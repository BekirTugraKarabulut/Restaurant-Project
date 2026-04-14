import 'package:flutter/material.dart';

class Categoriespage extends StatefulWidget {

  final username;
  const Categoriespage({super.key, this.username});

  @override
  State<Categoriespage> createState() => _CategoriespageState();
}

class _CategoriespageState extends State<Categoriespage> {

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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          const SizedBox(height: 20,),
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
            child: Center(
              child: Text(
                "Seçilen kategori: ${categories[selectedIndex]}",
                style: const TextStyle(fontSize: 18),
              ),
            ),
          )
        ],
      ),
    );
  }
}