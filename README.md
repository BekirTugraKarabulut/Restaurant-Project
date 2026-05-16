# Restaurant-Project

Bu proje; **Flutter**, **Spring Boot**, **PostgreSQL**, **Redis** ve **Docker** teknolojileri kullanılarak geliştirilmiş tam kapsamlı bir restaurant mobil uygulamasıdır.

Uygulama; müşteri, restaurant/yönetici ve kurye taraflarını kapsayan bir yemek sipariş sistemidir. Kullanıcılar ürünleri kategori bazlı görüntüleyebilir, sepete ürün ekleyebilir, adres bilgisi tanımlayabilir ve sipariş oluşturabilir. Yönetici tarafı gelen siparişleri takip edebilir, kurye tarafı ise teslimat süreçlerini yönetebilir.

---

## 🚀 Projenin Amacı

Bu projenin amacı, modern backend ve mobil teknolojileri kullanarak gerçek hayata yakın bir restaurant sipariş sistemi geliştirmektir.

Proje kapsamında:

- Mobil uygulama geliştirme
- RESTful API tasarımı
- JWT tabanlı kimlik doğrulama
- PostgreSQL ile ilişkisel veritabanı yönetimi
- Redis ile cache kullanımı
- Docker ile servisleri ayağa kaldırma
- Katmanlı mimari
- DTO yapısı
- Sepet ve sipariş yönetimi

gibi konular uygulanmıştır.

---

## 🛠️ Kullanılan Teknolojiler

### Mobil Tarafı

- Flutter
- Dart
- REST API entegrasyonu
- Lottie animasyonları
- Flutter Map
- LatLong2

### Backend Tarafı

- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- RESTful API
- Katmanlı Mimari
- DTO Pattern
- Redis Cache

### Veritabanıı

- PostgreSQL

### DevOps ve Araçlar

- Docker
- Docker Compose
- Maven
- Postman
- Git
- GitHub

---

## 📱 Uygulama Özellikleri

### Müşteri Özellikleri

- Kullanıcı kayıt olma
- Kullanıcı giriş yapma
- JWT token ile güvenli oturum yönetimi
- Ürün kategorilerini görüntüleme
- Yemek, tatlı, içecek ve atıştırmalık ürünlerini listeleme
- Sepete ürün ekleme
- Sepetteki ürünleri görüntüleme
- Sepet toplam tutarını hesaplama
- Adres bilgisi ekleme ve görüntüleme
- Sipariş oluşturma
- Sipariş durumunu takip etme

### Restaurant / Yönetici Özellikleri

- Ürünleri yönetme
- Kategorileri yönetme
- Gelen siparişleri görüntüleme
- Sipariş durumunu güncelleme
- Müşteri siparişlerini takip etme

### Kurye Özellikleri

- Kendisine atanan siparişleri görüntüleme
- Teslimat durumunu güncelleme
- Sipariş teslim sürecini yönetme

---

## 🏗️ Proje Mimarisi

Backend tarafında katmanlı mimari kullanılmıştır.

```text
Controller Layer
      ↓
Service Layer
      ↓
Repository Layer
      ↓
Database
