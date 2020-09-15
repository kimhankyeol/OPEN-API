import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//KAKAO REST API 공통 호출 클래스
public class KakaoVisionCall {
    public static void main(String[] args) throws IOException {
        //카카오 url
        String urlString = "https://dapi.kakao.com/v2/vision/face/detect";
        //apikey
        String apikey = "";
        //authorize
        String userCredentials = "KakaoAK";
        //contenttype
        String contentType="application/x-www-form-urlencoded;charset=utf-8";
        String methodType="POST";
        //전송 파라미터 //string 변수 일떄  file 이나 이런것들은 커스텀 해야함
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // 파라미터 만들기
        params.add(new BasicNameValuePair("image_url", "https://img4.yna.co.kr/photo/cms/2019/05/02/02/PCM20190502000402370_P2.jpg"));
        params.add(new BasicNameValuePair("threshold","0.7"));

        CurlToJavaRestConverter curlConv = new CurlToJavaRestConverter(urlString, apikey, userCredentials, contentType, methodType, params);
        String result = curlConv.restAPI();
        System.out.println(result);
    }
}
