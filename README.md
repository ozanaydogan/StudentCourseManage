"/api/v1/auth" endpointi uzantısına atacağınız istekler için Auth işlemine gerek yoktur. fakat diğer endpointlere istek atabilmeniz için User tablosunda ADMIN rolü olan kullanıcı oluşturmanız gereklidir.

"/api/v1/auth/register" endpointinten ADMIN, USER, STUDENT, MANAGER rolleriyle kayıt olabilirsiniz, (başka rol girilirse hata handle edilmeli fakat o kısım yapılmadı, Bu yazdığım rollerle kullanıcı oluşturmaya özen gösterin

ADMIN rolüne sahip bir kullanıcının JWTsini kullanarak @RequestMapping'i "/api/v1/course", "/api/v1/student" olarak atanmış endpointlere istek atabilirsiniz. Postman'den Bearer Token'i Header için ayarlayıp istek atın yoksa  403 hatası alırsınız

DTO paketindeki response objelerini tek bir base BaseResponse adında abstract class oluşturup "message" degiskenini orada barındırabilirdim fakat kodlama isleminin sonuna dogru bu islemi akıl edebildim benim hatam, birçok yerde degisiklik yapmak gerekli olduğu için yetiştiremedim

React tarafını yetiştiremedim kusura bakmayın

