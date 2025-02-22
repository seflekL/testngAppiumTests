# eCommerceMobileProject_TestNG 🚀

Bu proje, **Appium + TestNG + Selenium** kullanılarak **mobil e-ticaret uygulaması testleri** yapmak için oluşturulmuştur.  
Testler, **Android ve iOS** platformlarında çalışabilir ve **JUnit, TestNG, Allure, Log4j** gibi araçlarla desteklenmiştir.

Bu projede kullanılan ana teknolojiler: **TestNG, Selenium, Appium, Java Faker, Apache POI, Log4j ve Allure**.

---

## 📌 Kullanılan Teknolojiler ve Bağımlılıklar

Bu proje aşağıdaki teknolojileri içermektedir:

- **TestNG** → Test yönetimi ve paralel test çalıştırma desteği sağlar.
- **Selenium** → Web ve mobil test otomasyonunu yönetir.
- **Appium** → Mobil cihazlar üzerinde testlerin çalışmasını sağlar.
- **Java Faker** → Test verisi üretmek için kullanılır.
- **Apache POI** → Excel dosyalarından veri okuma/yazma işlemleri için kullanılır.
- **Log4j** → Testler sırasında loglama yapılmasını sağlar.
- **Allure** → Test raporlarının görselleştirilmesi için kullanılır.

Projede kullanılan **Maven bağımlılıkları `pom.xml` dosyasında** tanımlıdır.

---

## 📌 Proje Yapısı

Projenin temel dosya ve klasör yapısı aşağıdaki gibidir:

```plaintext
eCommerceMobileProject_TestNG/
│── src/
│   ├── main/                 # Proje ana kaynak kodları
│   ├── test/                 # Test sınıfları
│   │   ├── testng.xml        # TestNG yapılandırma dosyası
│   │   ├── pages/            # Sayfa nesneleri (Page Object Model - POM)
│   │   ├── tests/            # Test senaryoları
│   │   ├── utils/            # Yardımcı metotlar (Reusable Methods)
│   ├── resources/            # Test verileri ve yapılandırma dosyaları
│── pom.xml                   # Maven bağımlılıkları ve yapılandırması
│── .gitignore                # Gereksiz dosyaları Git'ten hariç tutar
│── README.md                 # Proje dokümantasyonu

📌 Kurulum ve Testleri Çalıştırma
Projeyi çalıştırmak için aşağıdaki adımları takip edin.

1️⃣ Maven bağımlılıklarını yüklemek için şu komutu çalıştırın:
mvn clean install
2️⃣ Testleri çalıştırmak için:
mvn test
3️⃣ Belirli bir test sınıfını çalıştırmak için:
mvn -Dtest=TestClassName test
4️⃣ Allure Raporu oluşturmak için:
mvn clean test
allure serve target/allure-results
📌 Konfigürasyon
Testlerde kullanılacak bazı parametreler configuration.properties dosyasında tanımlıdır. Örnek dosya içeriği:
appium.server.url=http://127.0.0.1:4723/wd/hub
platform=ANDROID
device.name=emulator-5554
app.package=com.example.app
app.activity=com.example.app.MainActivity
Bu dosya, src/main/resources/configuration.properties yolunda bulunur.

📌 Test Senaryoları
Aşağıdaki test senaryoları mevcuttur:

✅ Login Testi: Kullanıcı giriş yapma senaryosu
✅ Ürün Arama: Kullanıcı ürün arayıp sepete ekleyebilir
✅ Sepet Testi: Sepete eklenen ürünlerin doğrulaması
✅ Ödeme Testi: Kullanıcı ödeme işlemi yapabilir
Testler TestNG ile organize edilmiştir ve testng.xml dosyası üzerinden yönetilir.
📌 Desteklenen Platformlar
Bu proje Appium ile hem Android hem de iOS cihazlarında çalıştırılabilir:
✅ Android (Appium + UiAutomator2)
✅ iOS (Appium + XCUITest)
📌 Katkıda Bulunma 🤝
Projeye katkıda bulunmak istiyorsanız, fork edip kendi branch’inizde geliştirme yapabilirsiniz.
Pull Request göndermeden önce testlerin başarılı çalıştığından emin olun.

Projeyi klonlamak için:
git clone https://github.com/seflekL/eCommerceMobileProject_TestNG.git
cd eCommerceMobileProject_TestNG
git checkout -b feature-yeni-ozellik
Değişikliklerinizi ekleyip commit yapın:
git add .
git commit -m "Yeni özellik eklendi"
git push origin feature-yeni-ozellik
Daha sonra Pull Request (PR) göndererek değişikliklerinizi paylaşabilirsiniz.
📌 Lisans
Bu proje MIT Lisansı altında yayımlanmıştır. Kullanım serbesttir. 🎉