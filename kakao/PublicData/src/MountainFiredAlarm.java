import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MountainFiredAlarm  {
    public static void main(String[] args) throws IOException {
        /*지형, 임상, 기상자료를 이용하여 전국 시․군․구 행정구역에 매시간 산불위험 상황을 제공하며, 12시 ~ 21시까지 3시간 간격으로 이틀(48시간) 유관부처에 정보를 제공하고 조회 합니다.*/
//        https://www.data.go.kr/data/15059263/openapi.do 원래는 여기거 써야되는게맞음

        //api키와  version은 필수값 pdf 문서 참조 (산불위험지수)
        String ServiceKey = "";
        String version = "1.1";
        //전국인지 시도인지 읍면동인지   이것도 문서참조 여기서는 시도로 고정
        String gubun = "sido";
        //지역코드 pdf 참조 //html 에서 select 만들어서 onchange 걸어서 11 ,26 코드 바뀌게  11은 서울 26은 부산
        String localAreaCode = "11";

        //urlString 만드는 StringBuilder
        StringBuilder urlBuilder = new StringBuilder("http://know.nifos.go.kr/openapi/forestPoint/forestPointListSearch.do"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("keyValue","UTF-8") + "=" +ServiceKey);
        urlBuilder.append("&" + URLEncoder.encode("version","UTF-8") + "=" + URLEncoder.encode(version, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("gubun","UTF-8") + "=" + URLEncoder.encode(gubun, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("localArea","UTF-8") + "=" + URLEncoder.encode(localAreaCode, "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        String xml_result = sb.toString();
        //이건 뭔지 모르겠음 xml 파싱할떄  //// ㄴ 일단 xml 을 json 으로 바꿔야 jsp 에서 사용하기 편하기 떄문에
        int PRETTY_PRINT_INDENT_FACTOR = 4;

        try {
            JSONObject xmlJSONObj = XML.toJSONObject(xml_result);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
    }
}
