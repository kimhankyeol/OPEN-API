import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;
public class KakaoVisionAPI  {
    public static void main(String[] args) {
        try {
            String urlString = "https://dapi.kakao.com/v2/vision/face/detect";
            String apikey = "";
            String userCredentials = "KakaoAK "+apikey;

            System.out.println(userCredentials);

            HttpClient client = HttpClientBuilder.create().build();            // 전송방식 HttpGet, HttpPost방식
            HttpPost postRequest = new HttpPost(urlString); //POST 메소드 URL 생성
            postRequest.addHeader(HttpHeaders.AUTHORIZATION,userCredentials);
            //헤더 추가
//            HttpHeaders.AUTHORIZATION
//            postRequest.setHeader("Content-Type","application/x-www-form-urlencoded");
//            postRequest.setHeader("Accept", "x-www-form-urlencoded;charset=utf-8");
//            postRequest.setHeader("Connection", "keep-alive");
            postRequest.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");


            List<NameValuePair> param = new ArrayList<NameValuePair>();
            // 파라미터 만들기
            param.add(new BasicNameValuePair("image_url", "https://img4.yna.co.kr/photo/cms/2019/05/02/02/PCM20190502000402370_P2.jpg"));
            param.add(new BasicNameValuePair("threshold","0.7"));
            // 파라미터적용
            postRequest.setEntity(new UrlEncodedFormEntity(param));

            // 응답처리
            HttpResponse response = client.execute(postRequest);
            //Response 출력
            // 스테이터스 가져오기
            System.out.println(response.getStatusLine());
            System.out.println(response.toString());


//            if (response.getStatusLine().getStatusCode() == 200) {
//                ResponseHandler<String> handler = new BasicResponseHandler();
//                String body = handler.handleResponse(response);
//                System.out.println(body);
//            } else {
//                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
//            }

        } catch (Exception e){
            System.err.println(e.toString());
        }


    }
}
