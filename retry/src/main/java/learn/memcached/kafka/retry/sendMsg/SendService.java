package learn.memcached.kafka.retry.sendMsg;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/13
 * @Time:17:23
 */
@Service
public class SendService {

    @Autowired
    private RetryHandler retryHandler;

    public void sendMsg(){
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRetryHandler(retryHandler)
                .build();
        HttpGet httpGet = new HttpGet("http://www.begincodee.net");  //不存在的域名
        try{
            HttpResponse response =  httpClient.execute(httpGet, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
