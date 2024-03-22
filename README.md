"/api/v1/auth" endpointi uzantısına atacağınız istekler için Auth işlemine gerek yoktur. fakat diğer endpointlere istek atabilmeniz için User tablosunda ADMIN rolü olan kullanıcı oluşturmanız gereklidir.

"/api/v1/auth/register" endpointinten ADMIN, USER, STUDENT, MANAGER rolleriyle kayıt olabilirsiniz, (başka rol girilirse hata handle edilmeli fakat o kısım yapılmadı, Bu yazdığım rollerle kullanıcı oluşturmaya özen gösterin

ADMIN rolüne sahip bir kullanıcının JWTsini kullanarak @RequestMapping'i "/api/v1/course", "/api/v1/student" olarak atanmış endpointlere istek atabilirsiniz. Postman'den Bearer Token'i Header için ayarlayıp istek atın yoksa  403 hatası alırsınız

DTO paketindeki response objelerini tek bir base BaseResponse adında abstract class oluşturup "message" degiskenini orada barındırabilirdim fakat kodlama isleminin sonuna dogru bu islemi akıl edebildim benim hatam, birçok yerde degisiklik yapmak gerekli olduğu için yetiştiremedim

React tarafını yetiştiremedim kusura bakmayın

![1-TOKEN belirtmeden istek attıgım icin hata aldım yetkili birisinin ADMIN birisinin istek atması gerekiyor ADMIN olan birinin tokenini kullanalım](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/a8623402-1b88-4f69-a7a1-5aed80125bdc)

1-TOKEN belirtmeden istek attıgım icin hata aldım yetkili birisinin ADMIN birisinin istek atması gerekiyor ADMIN olan birinin tokenini kullanalım

![2](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/9b4944d0-5d17-42d5-aaa6-403ec324d12f)

2-

![3](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/79404d6a-662c-48ed-bc17-0ba5085f5f40)

3-

![4 az once denedigim JWT ile hata aldım suresi dolmus](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/84703780-ed93-4160-9630-d5e0ed7e7446)

4- az once denedigim JWT ile hata aldım suresi dolmus

![5 yeni bir USER olusturuyorum ADMIN rolunde olan TOKENI de refresh edebilirim](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/012ece1e-fce0-40dc-a1c3-562c9dca08fc)

5- yeni bir USER olusturuyorum ADMIN rolunde olan TOKENI de refresh edebilirim

![6 yeni bir user admin olustu simdi tekrar istek atalım](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/4e0a537b-7a1c-4499-9147-7cbfe00cd4c2)

6- yeni bir user admin olustu simdi tekrar istek atalım

![7](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/bd9a2d39-45a2-4718-be91-cd44d95ea3ab)

7-

![8 tokenimi buraya yazıyorum](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/9d1cd21d-9a96-40cb-ab49-1b66fdb84702)

8- tokenimi buraya yazıyorum

![9 ve görüldügü gibi beden dersini olusturdum](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/f81eab28-4ef6-40a2-b8d5-f6a154968d6f)

9- ve görüldügü gibi beden dersini olusturdum

![10 simdi az once olusturdugumuz beden dersini silelim](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/8a39e7d4-7ade-48a6-be99-0b548f25c6bf)

10- simdi az once olusturdugumuz beden dersini silelim

![11 artik isteklerimi admin userimin tokeniyle yapıyorum eger student veya manager rolunde birinin tokeniyle istek atarsam hata alırım](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/c6b9b884-4086-4b39-968b-8c529ee8bc28)

11- artik isteklerimi admin userimin tokeniyle yapıyorum eger student veya manager rolunde birinin tokeniyle istek atarsam hata alırım

![12 ve basarili bir sekilde silindi](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/bce1aec9-512c-48de-90d0-3fdd3a4880ad)

12- ve basarili bir sekilde silindi

![13](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/a9636061-02d6-4a2e-b217-594ae09201f8)

13-

![14 peki diyelim 12 idli kursu silmeye calisalım bu kurs Fizik dersi](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/77b6144c-358c-45ac-af97-e455aba9f87a)

14- peki diyelim 12 idli kursu silmeye calisalım bu kurs Fizik dersi

![15 hata alırım cünkü bu kurs bir ogrenciye atanmıs halde ilk olarak bu ogrenciden kursu kaldırmam gerekli kaldiralim](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/dfc8ea41-c1df-4d64-adeb-d53b66188e79)

15- hata alırım cünkü bu kurs bir ogrenciye atanmıs halde ilk olarak bu ogrenciden kursu kaldırmam gerekli kaldiralim

![16 6 numarali iddeki ogrenciden kursu kaldırdım cunku sadece bu ogrenci kursa sahipti ve basarılı sonuc aldım](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/5da6e991-c719-476d-8488-9300c8387ba6)

16- 6 numarali iddeki ogrenciden kursu kaldırdım cunku sadece bu ogrenci kursa sahipti ve basarılı sonuc aldım

![17 goruldugu gibi fizik dersi 6 numaralı ogrenciden kalktı](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/5098f4b3-ade7-4f79-8bb6-905e4018129f)

17- goruldugu gibi fizik dersi 6 numaralı ogrenciden kalktı

![18 az önce fizik dersini silerken hata almıstık cünkü kayıtlı ogrenciler vardı kayıtlı ogrencileri kaldırdıgımız icin kursuda silebildik](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/bf59edf5-b578-4d57-934e-72e48f211b0b)

18- az önce fizik dersini silerken hata almıstık cünkü kayıtlı ogrenciler vardı kayıtlı ogrencileri kaldırdıgımız icin kursuda silebildik

![19 kayıtlı tüm kursları getirdik](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/fc0c6a50-3e49-4602-b5d8-2c476f2cc4d1)

19- kayıtlı tüm kursları getirdik

![20 simdi ogrencide kayıtlı tüm dersleri listeledim](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/af62c520-175e-468c-af06-857d23874cb3)

20- simdi ogrencide kayıtlı tüm dersleri listeledim

![21 eger idsinbi belirttigimiz ogrenci yoksa hata doner](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/df086def-dab2-4ba0-b153-a89c5f6e4694)

21- eger idsinbi belirttigimiz ogrenci yoksa hata doner
