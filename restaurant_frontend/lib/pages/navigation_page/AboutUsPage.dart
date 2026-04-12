import 'package:flutter/material.dart';

class Aboutuspage extends StatefulWidget {

  final String username;
  const Aboutuspage({super.key, required this.username});

  @override
  State<Aboutuspage> createState() => _AboutuspageState();
}

class _AboutuspageState extends State<Aboutuspage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Center(
          child: Column(
            children: [
              Image.asset("images/restaurant.jpeg"),
              const SizedBox(height: 20),
              Padding(
                padding: const EdgeInsets.all(15.0),
                child: Container(
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(10)),
                    border: Border.all(
                      color: Colors.red
                    )
                  ),
                  child: Column(crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Text(style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),
                            "Restoranımıza hoş geldiniz!  Size mümkün olan en iyi yemek deneyimini sunmaya kendimizi adadık.  Menümüzde taze ve yüksek kaliteli malzemelerle hazırlanan çok çeşitli lezzetli yemekler bulunmaktadır. İster doyurucu bir yemek ister hafif bir atıştırmalık isteyin, herkese uygun bir seçeneğimiz var. Güler yüzlü personelimiz, harika vakit geçirmenizi ve yemeğinizin tadını çıkarmanızı sağlamak için burada. Restoranımızı tercih ettiğiniz için teşekkür ederiz, en yakın zamanda size hizmet vermeyi umuyoruz"
                        ,
                          textAlign: TextAlign.start,
                          softWrap: true,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              Row(mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("İletişim Numarası : 0-530-725-11-58   ",style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),),
                  Icon(Icons.phone , color: Colors.black,size: 20,)
                ],
              ),
              const SizedBox(height: 15,),
              Row(mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("Kurye Numarası : 0-545-000-44-58      ", style: TextStyle(color: Colors.black , fontWeight: FontWeight.bold),),
                  Icon(Icons.phone , color: Colors.black,size: 20,)
                ],
              ),
              const SizedBox(height: 80,),
              Text("© 2024 Restoranımız. Tüm hakları saklıdır.",)
            ],
          ),
        ),
    );
  }
}
