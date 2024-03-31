"/api/v1/auth" endpointi uzantısına atacağınız istekler için Auth işlemine gerek yoktur. fakat diğer endpointlere istek atabilmeniz için User tablosunda ADMIN rolü olan kullanıcı oluşturmanız gereklidir.

"/api/v1/auth/register" endpointinten ADMIN, USER, STUDENT, MANAGER rolleriyle kayıt olabilirsiniz, (başka rol girilirse hata handle edilmeli fakat o kısım yapılmadı, Bu yazdığım rollerle kullanıcı oluşturmaya özen gösterin

ADMIN rolüne sahip bir kullanıcının JWTsini kullanarak @RequestMapping'i "/api/v1/course", "/api/v1/student" olarak atanmış endpointlere istek atabilirsiniz. Postman'den Bearer Token'i Header için ayarlayıp istek atın yoksa  403 hatası alırsınız

DTO paketindeki response objelerini tek bir base BaseResponse adında abstract class oluşturup "message" degiskenini orada barındırabilirdim 

Postman'de APIleri deneyebilmeniz için collections oluşturdum "studentManagementAPI.postman_collection.json" dosyasına bakınız

Swagger için : 

http://localhost:8080/swagger-ui/index.html


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

############################################################################################################################
############################################################################################################################

![1 kayıtlı tum ogrencileri getirdik](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/cc1997d6-3462-4783-9e06-c4e522356a23)

1- kayıtlı tum ogrencileri getirdik

![2 idye gore student getirdik](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/10b7ff9d-bfd4-413e-94a3-68d9e63e80fe)

2- idye gore student getirdik

![3 student yoksa null doner](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/aa267b3a-d880-41ce-aa0d-91a05c8cf802)

3- student yoksa null doner

![4 simdi ogrenciye ders atadık](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/d6bb9e09-5146-4ff7-821f-cad5354f09d1)

4- simdi ogrenciye ders atadık

![5](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/8e0ab4f0-2e5b-49c6-8568-3c9f5994507a)

5-

![6 eğer ogrenci bu kursa sahipse hata verir](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/237df2f6-163b-4f02-93f1-7e8cf45905e9)

6- eğer ogrenci bu kursa sahipse hata verir

![7 eger boyle bir ogrenci yoksa da hata verir](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/2c5a239a-44e3-4dc9-b55a-bc57065f42fe)

7- eger boyle bir ogrenci yoksa da hata verir

![8 simdi müzik dersini ogrenciden kaldıralım](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/a665aff7-7e70-4c33-a3f8-8e07135ec852)

8- simdi müzik dersini ogrenciden kaldıralım

![9](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/770afc40-8ed1-43ec-8cd7-4f79231b9d86)

9-

![10 simdi 17 id numaralı kursa kayıtlı olan ogrencileri listeledik](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/fbbcf2b8-2864-42df-a8e6-34a1e27b55ad)

10- simdi 17 id numaralı kursa kayıtlı olan ogrencileri listeledik

![11 simdi 18 id numaralı kursa kayitlı ogrencileri listeledik hic ogrenci yok](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/9be93b2d-2339-4157-80f2-13074db03b57)

11- simdi 18 id numaralı kursa kayitlı ogrencileri listeledik hic ogrenci yok


############################################################################################################################
############################################################################################################################

![1 simdi bir Student useri olusturalım Studentlerimizin hepsi User i miras alır aynı tabloda barınacakları sekilde ayarladım](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/c1dd4276-77dc-4751-bfc9-0124af699c6a)

1- simdi bir Student useri olusturalım Studentlerimizin hepsi User i miras alır aynı tabloda barınacakları sekilde ayarladım

![2 basarili](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/d75c1779-e863-4469-b737-1f28c12c5dd5)

2- basarili

![3 simdi authenticate islemi yapalım ve tokenimizi yenileyelim 13 numaralı id deki kullanıcı icin tokenleri yenileyecegim](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/de56f395-8d13-4370-83fd-1a802fb3704b)

3- simdi authenticate islemi yapalım ve tokenimizi yenileyelim 13 numaralı id deki kullanıcı icin tokenleri yenileyecegim

![4 basarili yeni token dondu](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/6b57ad43-4361-4c84-ac70-a5113e5d5a5a)

4- basarili yeni token dondu

![5 görüldügü gibi eski token expired ve revoke degerleri true olarak atandı ve yeni token olustu](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/020df44c-6ee1-412f-9ef0-e717327528bd)

5- görüldügü gibi eski token expired ve revoke degerleri true olarak atandı ve yeni token olustu

![6 simdi 13 numarali id deki useri silelim](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/e09e6b55-6a3f-42d0-a92d-983853702e6a)

6- simdi 13 numarali id deki useri silelim

![7 13 numarali useri sildigimizde Cascade den dolayı 13 numaralı idnin tokenlerde silinecek](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/3223ad92-cd90-4d57-b373-d7fff65f45b2)

7- 13 numarali useri sildigimizde Cascade den dolayı 13 numaralı idnin tokenlerde silinecek

![8 basarili](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/a93ba2ec-6f6d-4700-9a0b-4d326449739f)

8- basarili

![9 goruldugu gibi tokenlar gitti](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/1a8fb565-7b47-4ae5-9cce-6bf27d0f497b)

9- goruldugu gibi tokenlar gitti

![10 peki üzerine ders atanmıs bir student silinebilir mi](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/a0f0058f-dbad-4189-a4f3-71e764721488)

10- peki üzerine ders atanmıs bir student silinebilir mi

![11 hayır çünkü ilk olarak bu student'dan dersleri kaldırmamız gerek kaldırdıktan sonra silebiliriz](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/169fbefe-095e-4230-9f95-a68b46e7a7f8)

11- hayır çünkü ilk olarak bu student'dan dersleri kaldırmamız gerek kaldırdıktan sonra silebiliriz

############################################################################################################################
############################################################################################################################

![1 simdi user'imizin parolasını degistirelim](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/633b51f6-b736-4ccb-aa35-0c3a5a3d83ce)

1- simdi user'imizin parolasını degistirelim

![2 su an ki parolası](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/d6d6a6f3-bd98-4e65-9cb2-8784e0bc4f4d)

2- su an ki parolası

![3 parolası degisti](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/e5f8c969-68bb-477f-9150-a094d5ff78fd)

3- parolası degisti















