import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;
public class CurlToJavaRestConverter {
    private String urlString;
    private String apiKey;
    private String userCredentials;
    private String contentType;
    private String methodType;
    private List<NameValuePair> params;
    //결과 담을 스트링 변수
    private String body="";

    //생성자 오버로딩
    public CurlToJavaRestConverter(String urlString, String apiKey, String userCredentials, String contentType, String methodType, List<NameValuePair> params) {
        this.urlString = urlString;
        this.apiKey = apiKey;
        this.userCredentials = userCredentials + " " + apiKey;
        this.contentType = contentType;
        this.methodType = methodType;
        this.params = params;
    }


    //예외처리
    public String restAPI() throws IOException {
        if (this.methodType.equalsIgnoreCase("POST")) {
            //method post 일때
            try {
                HttpClient client = HttpClientBuilder.create().build();            // 전송방식 HttpGet, HttpPost방식
                HttpPost postRequest = new HttpPost(this.urlString); //POST 메소드 URL 생성
                postRequest.addHeader(HttpHeaders.AUTHORIZATION, this.userCredentials);
                postRequest.setHeader("Content-Type", this.contentType);
                postRequest.setEntity(new UrlEncodedFormEntity(this.params));

                // 응답처리
                HttpResponse response = client.execute(postRequest);
                //결과 상태가 200 이면 성공 200 이 아니면 실패
                if (response.getStatusLine().getStatusCode() != 200) {
                    System.out.println("response is error : " + response.getStatusLine().getStatusCode());
                } else {
                    ResponseHandler<String> handler = new BasicResponseHandler();
                    //결과 body 꺼내옴
                    this.body = handler.handleResponse(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.toString());
            }
        }else{
            //method get일떄
            try {
                HttpClient client = HttpClientBuilder.create().build();            // 전송방식 HttpGet, HttpPost방식
                HttpGet getRequest = new HttpGet(this.urlString+"?"+ URLEncodedUtils.format(this.params, "UTF-8")+"&serviceKey="+this.apiKey); //GET 메소드 URL 생성
                System.out.println(getRequest.getRequestLine());
                //헤더가 있을수도 없을수도 있기때문에 분기처리 해주는것도 좋을듯
//                getRequest.addHeader(HttpHeaders.AUTHORIZATION, this.userCredentials);
//                getRequest.setHeader("Content-Type", this.contentType);

                // 응답처리
                HttpResponse response = client.execute(getRequest);
                //결과 상태가 200 이면 성공 200 이 아니면 실패
                if (response.getStatusLine().getStatusCode() != 200) {
                    System.out.println("response is error : " + response.getStatusLine().getStatusCode());
                } else {
                    ResponseHandler<String> handler = new BasicResponseHandler();
                    //결과 body 꺼내옴
                    this.body = handler.handleResponse(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.toString());
            }
        }
        return this.body;
    }

}