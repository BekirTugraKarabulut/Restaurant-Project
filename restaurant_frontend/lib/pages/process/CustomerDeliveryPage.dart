import 'package:flutter/material.dart';
import '../../services/customer/GetOrdersByUsername.dart';

class Customerdeliverypage extends StatefulWidget {
  final String username;

  const Customerdeliverypage({super.key, required this.username});

  @override
  State<Customerdeliverypage> createState() => _CustomerdeliverypageState();
}

class _CustomerdeliverypageState extends State<Customerdeliverypage> {

  final GetOrdersByUsername getOrdersByUsername = GetOrdersByUsername();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey.shade100,
      appBar: AppBar(
        backgroundColor: Colors.red,
        title: const Text(
          "Geçmiş Siparişlerim",
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
        ),
        centerTitle: true,
        leading: IconButton(
          onPressed: () {
            Navigator.pop(context);
          },
          icon: const Icon(
            Icons.arrow_back_ios_new_outlined,
            color: Colors.white,
          ),
        ),
      ),
      body: FutureBuilder<List<Map<String, dynamic>>>(
        future: getOrdersByUsername.getOrdersByUsername(widget.username),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(
              child: CircularProgressIndicator(color: Colors.red),
            );
          }

          if (snapshot.hasError) {
            return const Center(
              child: Text(
                "Siparişler yüklenirken hata oluştu",
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
            );
          }

          if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(
              child: Text(
                "Henüz siparişiniz bulunmuyor",
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
            );
          }

          final orders = snapshot.data!;

          return ListView.builder(
            padding: const EdgeInsets.all(12),
            itemCount: orders.length,
            itemBuilder: (context, index) {
              final order = orders[index];

              return Card(
                elevation: 4,
                margin: const EdgeInsets.only(bottom: 12),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(15),
                ),
                child: ListTile(
                  contentPadding: const EdgeInsets.all(14),
                  leading: CircleAvatar(
                    backgroundColor: Colors.red.shade100,
                    child: const Icon(
                      Icons.receipt_long,
                      color: Colors.red,
                    ),
                  ),
                  title: Text(
                    "Sipariş No: ${order["payId"]}",
                    style: const TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  subtitle: Padding(
                    padding: const EdgeInsets.only(top: 8),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            const Icon(Icons.location_on, size: 18),
                            const SizedBox(width: 5),
                            Expanded(
                              child: Text(
                                order["address"] ?? "Adres yok",
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(height: 6),
                        Row(
                          children: [
                            const Icon(Icons.payments, size: 18),
                            const SizedBox(width: 5),
                            Text(
                              "${order["price"]} ₺",
                              style: const TextStyle(
                                color: Colors.red,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                  trailing: const Icon(
                    Icons.arrow_forward_ios,
                    size: 18,
                    color: Colors.grey,
                  ),
                ),
              );
            },
          );
        },
      ),
    );
  }
}