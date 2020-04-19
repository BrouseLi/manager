package mimicweb.manager.util;


import mimicweb.manager.enums.ElementIpMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class SendMessageUtil {
    public static String sendMessage(String url,String policy){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url+"?policy="+policy).get().build();
        try (Response response=okHttpClient.newCall(request).execute()){
            return response.body().string();
        }catch (Exception e){
            return "link --"+url+"--- serverError";
        }
    }
    public static String sendKeepaliveMessage(String ip,String token){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://"+ip+":8080/stopKeepalive?token="+token).get().build();
        try(Response response=okHttpClient.newCall(request).execute()){
            return response.body().string();
        }catch (Exception e){
            return "link --"+ ElementIpMapper.fromTypeName(ip) +"--- serverError";
        }
    }
    public static String sendMysqlMessage(String ip,String token){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://"+ip+":8080/scheduleMysqlService?token="+token).get().build();
        try(Response response=okHttpClient.newCall(request).execute()){
            return response.body().string();
        }catch (Exception e){
            return "link --"+ ip +"--- mysqlServiceError";
        }
    }
    public static String sendProbeServer(String ip,String token,String probeName){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://"+ip+":8080/getProbe?token="+token+"&"+"probeName="+probeName).get().build();
        try(Response response=okHttpClient.newCall(request).execute()){
            return response.body().string();
        }catch (Exception e){
            return "link --"+ ip +"--- mysqlServiceError";
        }
    }
}
