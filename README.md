# Tutorial 1
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
    
    