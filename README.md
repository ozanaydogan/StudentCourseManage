"/api/v1/auth" endpointi uzantısına atacağınız istekler için Auth işlemine gerek yoktur. fakat diğer endpointlere istek atabilmeniz için User tablosunda ADMIN rolü olan kullanıcı oluşturmanız gereklidir.

"/api/v1/auth/register" endpointinten ADMIN, USER, STUDENT, MANAGER rolleriyle kayıt olabilirsiniz, (başka rol girilirse hata handle edilmeli fakat o kısım yapılmadı, Bu yazdığım rollerle kullanıcı oluşturmaya özen gösterin

ADMIN rolüne sahip bir kullanıcının JWTsini kullanarak @RequestMapping'i "/api/v1/course", "/api/v1/student" olarak atanmış endpointlere istek atabilirsiniz. Postman'den Bearer Token'i Header için ayarlayıp istek atın yoksa  403 hatası alırsınız

DTO paketindeki response objelerini tek bir base BaseResponse adında abstract class oluşturup "message" degiskenini orada barındırabilirdim fakat kodlama isleminin sonuna dogru bu islemi akıl edebildim benim hatam, birçok yerde degisiklik yapmak gerekli olduğu için yetiştiremedim

React tarafını yetiştiremedim kusura bakmayın

![1-TOKEN belirtmeden istek attıgım icin hata aldım yetkili birisinin ADMIN birisinin istek atması gerekiyor ADMIN olan birinin tokenini kullanalım](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/a8623402-1b88-4f69-a7a1-5aed80125bdc)

1-TOKEN belirtmeden istek attıgım icin hata aldım yetkili birisinin ADMIN birisinin istek atması gerekiyor ADMIN olan birinin tokenini kullanalım

![2](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/9b4944d0-5d17-42d5-aaa6-403ec324d12f)

2

![3](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/79404d6a-662c-48ed-bc17-0ba5085f5f40)

3

![4 az once denedigim JWT ile hata aldım suresi dolmus](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/84703780-ed93-4160-9630-d5e0ed7e7446)

4 az once denedigim JWT ile hata aldım suresi dolmus

![5 yeni bir USER olusturuyorum ADMIN rolunde olan TOKENI de refresh edebilirim](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/012ece1e-fce0-40dc-a1c3-562c9dca08fc)

5 yeni bir USER olusturuyorum ADMIN rolunde olan TOKENI de refresh edebilirim

![6 yeni bir user admin olustu simdi tekrar istek atalım](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/4e0a537b-7a1c-4499-9147-7cbfe00cd4c2)

6 yeni bir user admin olustu simdi tekrar istek atalım

![7](https://github.com/ozanaydogan/StudentCourseManage/assets/49997690/bd9a2d39-45a2-4718-be91-cd44d95ea3ab)
