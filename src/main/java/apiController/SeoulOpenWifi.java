package apiController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SeoulOpenWifi {
    private final String HOST = "http://openapi.seoul.go.kr:8088";
    private final String API_KEY = "684a557976636b7335396a51505845";
    private final String TYPE = "JSON";
    private final String SERVICE = "TbPublicWifiInfo";
    private String startIndex = "1";

    final String[] API_JSON_KEYS = {
            "X_SWIFI_MGR_NO",
            "X_SWIFI_WRDOFC",
            "X_SWIFI_MAIN_NM",
            "X_SWIFI_ADRES1",
            "X_SWIFI_ADRES2",
            "X_SWIFI_INSTL_FLOOR",
            "X_SWIFI_INSTL_TY",
            "X_SWIFI_INSTL_MBY",
            "X_SWIFI_SVC_SE",
            "X_SWIFI_CMCWR",
            "X_SWIFI_CNSTC_YEAR",
            "X_SWIFI_INOUT_DOOR",
            "X_SWIFI_REMARS3",
            "LAT",
            "LNT",
            "WORK_DTTM"
    };

    private String getListTotalCount() throws IOException {
        String url = HOST + "/"
                + API_KEY + "/"
                + TYPE + "/"
                + SERVICE + "/"
                + startIndex + "/"
                + startIndex;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        String json = Objects.requireNonNull(response.body()).string();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);

        return element
                .getAsJsonObject().get("TbPublicWifiInfo")
                .getAsJsonObject().get("list_total_count")
                .getAsString();
    }

    public ArrayList<WifiInfo> getWifiList(){
        ArrayList<WifiInfo> wifiList = new ArrayList<>();
        try {
            String totalCount = this.getListTotalCount();

            int cnt = 1;
            while(true){
                startIndex = String.valueOf(cnt);
                String endIndex = String.valueOf(Math.min(Integer.parseInt(totalCount), cnt+999));

                String url = HOST + "/"
                        + API_KEY + "/"
                        + TYPE + "/"
                        + SERVICE + "/"
                        + startIndex + "/"
                        + endIndex;

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                String json = Objects.requireNonNull(response.body()).string();

                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(json);

                JsonArray jsonList = element
                        .getAsJsonObject().get("TbPublicWifiInfo")
                        .getAsJsonObject().get("row").getAsJsonArray();

                for(int i = 0; i < jsonList.size(); i++){
                    WifiInfo curInfo = new WifiInfo();

                    JsonElement cur = jsonList.get(i);

                    curInfo.setMgrNo(cur.getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString());
                    curInfo.setWrdofc(cur.getAsJsonObject().get("X_SWIFI_WRDOFC").getAsString());
                    curInfo.setMainName(cur.getAsJsonObject().get("X_SWIFI_MAIN_NM").getAsString());
                    curInfo.setAdres1(cur.getAsJsonObject().get("X_SWIFI_ADRES1").getAsString());
                    curInfo.setAdres2(cur.getAsJsonObject().get("X_SWIFI_ADRES2").getAsString());
                    curInfo.setInstlFloor(cur.getAsJsonObject().get("X_SWIFI_INSTL_FLOOR").getAsString());
                    curInfo.setInstlTy(cur.getAsJsonObject().get("X_SWIFI_INSTL_TY").getAsString());
                    curInfo.setInstlMby(cur.getAsJsonObject().get("X_SWIFI_INSTL_MBY").getAsString());
                    curInfo.setSvcSe(cur.getAsJsonObject().get("X_SWIFI_SVC_SE").getAsString());
                    curInfo.setCmcwr(cur.getAsJsonObject().get("X_SWIFI_CMCWR").getAsString());
                    curInfo.setCnstcYear(cur.getAsJsonObject().get("X_SWIFI_CNSTC_YEAR").getAsString());
                    curInfo.setInOutDoor(cur.getAsJsonObject().get("X_SWIFI_INOUT_DOOR").getAsString());
                    curInfo.setRemars3(cur.getAsJsonObject().get("X_SWIFI_REMARS3").getAsString());
                    curInfo.setLat(cur.getAsJsonObject().get("LAT").getAsDouble());
                    curInfo.setLnt(cur.getAsJsonObject().get("LNT").getAsDouble());
                    curInfo.setWorkDttm(cur.getAsJsonObject().get("WORK_DTTM").getAsString());

                    wifiList.add(curInfo);
                }
                if(Integer.parseInt(endIndex) == Integer.parseInt(totalCount)){
                    break;
                }
                cnt += 1000;
            }
            return wifiList;
        }catch (Exception e){
            return null;
        }
    }
}
