package learn.memcached.kafka.retry.sendMsg;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/13
 * @Time:19:00
 */
@Component
public class RetryHandler implements HttpRequestRetryHandler {

    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext context){
        if (executionCount >= 5) {
            return false;
        }
        if (exception instanceof IOException) {
            System.out.println("超时和其他异常都是在IO异常下的，为处理所有异常，抛出此异常！");
            return true;
        }
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();
        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
        if (idempotent) {
            return true;
        }
        return false;
    }
}
