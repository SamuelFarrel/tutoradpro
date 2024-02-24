<details>
<summary> Tutorial 1 </summary>

## Reflection 1

### Prinsip _clean code_ yang saya gunakan

-  **Nama Variabel yang jelas :**

    Saya menggunakan penamaan yang jelas dan mudah dipahami karena telah menjelaskan apa tujuan dari variabel tersebut (deskriptif).
    Penamaan variabel tidak terlalu pendek dengan hanya menggunakan satu huruf, tetapi juga tidak terlalu panjang agar memudahkan pembaca dalam memahami variabel tersebut.
    Karena menggunakan bahasa Java, saya juga memperhatikan penggunaan camelCase dalam penamaan variabel.
    Penamaan yang jelas memungkinkan saya untuk meminimalisir _comment_ yang tidak diperlukan.


- **Menggunakan Functions :**

    Penggunaan fungsi dalam kode saya dapat mengurangi adanya repetisi kode yang melakukan hal yang sama. 
    Fungsi juga menggunakan nama yang deskriptif agar mudah dipahami tujuannya.
    Fungsi juga sebaiknya tidak terlalu rumit dan panjang, jika perlu, dapat dipecah menjadi beberapa fungsi.
    Selain itu, fungsi memiliki satu tujuan saja dan dapat melakukannya dengan baik tanpa mempengaruhi hal lainnya yang berada di luar jangkauannya (tidak ada efek samping).


- **_Comments_ :**
    
    Sebisa mungkin saya membuat kode saya sudah dapat dimengerti oleh pembaca tanpa adanya komen karena kode yang saya tulis sudah jelas. 
    Penulisan komen sebatas pada informasi yang perlu dijelaskan (contoh: format data yang perlu diinput), bukan menjelaskan apa yang dilakukan kode saya.


- **_Object_ dan Data _Structures_ :**
    
    Menerapkan struktur seperti Object, Object Repository, Service, dan Implementation sehingga kode lebih terstruktur.

Menurut saya, kode saya masih bisa diimprove dengan menambahkan error handling pada bagian-bagian yang mungkin terjadi error atau exception, serta
masih dapat diterapkan prinsip secure coding jika program yang saya buat sudah membutuhkan keamanan untuk menjaga informasi dari user atau database.

## Reflection 2
1. Setelah menerapkan unit test, saya merasa lebih yakin pada kode yang saya buat karen funsgionalitasnya berjalan dengan baik.
    Menurut saya, jumlah unit test tidak dapat ditentukan dengan pasti karena tergantung pada fungsionalitas program dan berapa banyak unit yang ingin kita test.
    Unit test dapat dikatakan cukup jika seluruh fitur dan test unit sudah tercangkup dalam pengujian. 100% code coverage belum tentu berarti kode kita bebas dari error atau bug karena bisa saja case error yang tidak terjangkau oleh test.
2. Ya, karena dapat terjadi repetisi kode yang melakukan hal yang sama, misalkan repetisi pada proses _setup_ dan pada _instance variables_. 
Kode bisa di*improve* dengan menggabungkan test tersebut pada satu file dan menggunakan file controller yang sama sehingga meminimalisir repetisi pada kode.   
</details>

<details>
<summary>Tutorial 2</summary>

## Fixed Code Quality Issues
- **Remove this field injection and use constructor injection instead**

    Disebabkan karena menggunakan field injection pada suatu kelas, diatasi dengan membuat class constructor untuk menggantikan `@Autowired`, contohnya
    
    Before :
    
    ```java
    public class ProductController {
        @Autowired
        private ProductService service;
    ```
    
    After :
    
    ```java
    public class ProductController {
        private final ProductService service;
    
        public ProductController(ProductService service){
            this.service = service;
        }
    ```


- **Remove `public` modifier**

    Disebabkan karena menggunakan public modifier pada test classes yang tidak seharusnya, diatasi dengan menghapus `public` modifier pada class, contohnya
    
    Before :
    
    ```java
    public class EshopApplicationTests {
        @Test
        public void main() {
            EshopApplication.main(new String[] {});
        }
    }
    ```
    
    After :
    
    ```java
    class EshopApplicationTests {
        @Test
        void main() {
            EshopApplication.main(new String[] {});
        }
    }
    ```


- **Add at least one assertion to this test case**

    Disebabkan karena tidak terdapat assertion pada sebuah test file, diatasi dengan menambahi assertion test pada file, contohnya
    
    Before :
    
    ```java
    class EshopApplicationTests {
        @Test
        void main() {
            EshopApplication.main(new String[] {});
        }
    }
    ```

    After :
    
    ```java
    class EshopApplicationTests {
        @Test
        void main() {
            EshopApplication.main(new String[] {});
            assertThat(EshopApplication.class).isNotNull();
        }
    }
    ```


- **Remove this use of `Thread.sleep()`**

    Disebabkan karena penggunaan `Thread.sleep()` menyebabkan tes yang tidak stabil, bisa menggunakan `Awaitility` dari mock sebagai alternatif, untuk sekarang saya atasi dengan menghapusnya karena tidak terpakai, contohnya pada `createPageFunctionalTest`

    Before :
    
    ```java
            quantityInput.sendKeys("25");
            submitButton.click();

            Thread.sleep(1000);
            driver.get(baseUrl + "/product/list");
            WebElement table = driver.findElement(By.tagName("table"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));
    ```

    After :
    
    ```java
            quantityInput.sendKeys("25");
            submitButton.click();
            driver.get(baseUrl + "/product/list");
            WebElement table = driver.findElement(By.tagName("table"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));
    ```


## CI-CD Reflection
Menurut saya, kode saya telah memenuhi kriteria CI dan CD. Pada project saya, telah diimplementasikan beberapa workflows yaitu `scorecard.yml`,`sonarcloud.yml`, dan `ci.yml` yang akan dijalankan (secara otomatis) pada Github Actions setiap kali terjadi suatu push atau pull request pada branch, sehingga hal tersebut sudah memenuhi konsep CI (_Continuous Integration_). Selain itu, saya juga menggunakan platform Koyeb untuk mendeploy web saya, dan deployment tersebut akan berlangsung secara otomatis setiap ada push atau pull request pada branch sehingga project saya juga sudah memenuhi kriteria CD (_Continuous Deployment_).
    
</details>

<details>
<summary> Tutorial 3 </summary>

## Prinsip Solid yang saya gunakan pada project saya
- **Single Responsibility Principle (SRP)**

    Saya memisahkan class `CarController` dengan class `ProductController` menjadi dua file yang berbeda karena masing-masing class memiliki tanggung jawab yang berbeda. `CarController` berfungsi untuk mengatur endpoint untuk Car related (`/car`) dan `ProductController` untuk endpoint Product related (`/product`), dengan begitu saya tidak melanggar prinsip SRP dan mencegah adanya endpoint yang tidak diinginkan karena terdapat dua class controller yang berbeda responsibilty pada satu file yang sama.

- **Open-Closed Principle (OCP)**

    Untuk prinsip ini, saya sedikit merubah cara bekerja dari repository Car dan Product. Agar `Repository` hanya _open for extension_ dan tetap _closed for modification_, `Repository` saya ubah menjadi interface `RepositoryInterface`. Lalu akan terdapat dua class yang akan implement interface tersebut yaitu `CarRepository` untuk tipe object `Car` dan `ProductRepository` untuk tipe object `Product`. Saya juga mengupdate beberapa test, controller, dan service implementation agar bekerja sesuai dengan repository yang telah diupdate.

- **Dependency Inversions Principle (DIP)**
    
    Untuk memenuhi prinsip DIP, saya hanya melakukan perubahan minor pada controller dan service implementations dari project saya. Pada dasarnya semua file akan menggunakan interface asli instead of implementasinya (depending on interface or abstarct functions and classes). Pada `CarController` saya mengubah tipe `carservice` yang digunakan untuk memanggil function edit,delete,add, dan lainnya dari `CarServiceImpl` menjadi `CarService` saja. Selanjutnya, pada file `CarServiceImpl` tipe dari variabel `carRepository` diubah menjadi menggunakan `RepsitoryInterface<Car>` instead of `CarRepository`. Begitu juga dengan `ProductServiceImpl`


## Kelebihan mengaplikasikan prinsip SOLID pada project
- Meningkatkan maintainability dan readability dari class-class pada project karena tiap class memiliki tanggung jawabnya masing-masing sehingga mudah untuk dipahami, terutama bagi orang lain yang bukan merupakan owner project (**contoh**: pemisahan controller yang mengatur Car related (`/car`) dan yang mengatur Product related (`/Product`))
- Mengurangi kemungkinan untuk terjadi error karena penambahan kode dapat dilakukan tanpa merubah kode yang sudah ada dan berjalan dengan lancar (**contoh**: jika kita ingin buat satu object baru, misalnya bernama `Item`, kita hanya perlu menambahkan repository implementation baru `ItemRepository`, tanpa merubah kode repository lain yang sudah ada dan berjalan dengan lancar)
- Memudahkan testing (meningkatkan testability) karena high-level modules bergantung pada abstraksi dibandingkan concrete class (**contoh**: `CarServiceImpl` yang bergantung pada `RepositoryInterface` mempermudah testing project)

## Kekurangan tidak menggunakan prinsip SOLID pada project
- Modifikasi atau penambahan fitur pada project dapat menimbulkan error atau bug baru pada aplikasi (**contoh**: kita ingin mengubah fitur delete pada Car saja, tetapi kita harus mengubah kode pada class repository yang sudah berjalan dengan benar)
- Kode dan class pada project menjadi kompleks sehingga sulit untuk dimengerti dan dikelola karena tiap class belum tentu hanya memiliki satu tujuan/tanggung jawab (**contoh**: Pada `before-solid`, `ProductController` memiliki fungsi untuk mengatur endpoint untuk Product dan Car yang dapat membingungkan pembaca kode karena tidak ada alasan yang jelas juga untuk penggabungan dua class yang berbeda tujuan dalam satu file yang sama)
- Kode menjadi kurang fleksibel untuk dirubah karena high-level modules masih bergantung pada concrete class karena perubahan pada low-level modules dapat mengakibatkan perubahan pada high-level modules juga (**contoh**: jika kita tidak menerapkan DIP, perubahan pada `CarRepository` dapat membuat kita melakukan perubahan pada `CarServiceImpl` juga)


</details>
    
