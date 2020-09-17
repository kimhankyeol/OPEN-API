import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Weather {
    public static void main(String[] args) throws IOException {
        //openapi 날씨 동네예보
        String urlString = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtFcst";
        //apikey
        String apikey = "";
        //"authorize
        String userCredentials = "";
        //contenttype
        String contentType="application/x-www-form-urlencoded;charset=utf-8";
        String methodType="GET";
        //전송 파라미터 //string 변수 일떄  file 이나 이런것들은 커스텀 해야함
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // 파라미터 만들기 // nx ny 동네 좌표
        params.add(new BasicNameValuePair("numOfRows","10"));
        params.add(new BasicNameValuePair("pageNo","1"));
        params.add(new BasicNameValuePair("dataType","XML"));
        params.add(new BasicNameValuePair("base_date","20200917"));
        params.add(new BasicNameValuePair("base_time","0630"));
        params.add(new BasicNameValuePair("nx","59"));
        params.add(new BasicNameValuePair("ny","127"));

        CurlToJavaRestConverter curlConv = new CurlToJavaRestConverter(urlString, apikey, userCredentials, contentType, methodType, params);
        String result = curlConv.restAPI();
        System.out.println(result);
    }
}
