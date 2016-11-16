package com.smart.message.invoker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.smart.mvc.exception.ApplicationException;

/**
 * <b>Description:</b><br>
 * @author jeason
 */
public class HttpInvoker{
	
	private static final String UTF8 = "utf-8";
    private static final HttpClient httpClient = getHttpClient();

    public String post(String url,Map<String, Object> headres,String stringEntity) {
    	
        HttpPost post = new HttpPost(url);
        if(headres!=null){
        	Set<Entry<String, Object>> headEntrySet=headres.entrySet();
        	for(Entry<String, Object> entry:headEntrySet){
        		post.setHeader(entry.getKey(), entry.getValue().toString());
        	}
        }
        
        try{
            post.setEntity(new StringEntity(stringEntity, UTF8));
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(response.getEntity(),UTF8);
            }
            throw new ApplicationException("请求失败,http响应码:"+response.getStatusLine().getStatusCode());
        } catch (Exception e){
            throw new ApplicationException(e);
        }
    }
    
    public String post(String url,Map<String, Object> datas) {
    	
    	HttpPost post = new HttpPost(url);
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if(datas!=null){
				Set<Entry<String, Object>> entryset=datas.entrySet();
				for(Entry<String, Object> entry:entryset){
					if(entry.getValue()!=null){
						params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
					}
				}
			}
			
        	post.setEntity(new UrlEncodedFormEntity(params, UTF8));
			HttpResponse response = httpClient.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), UTF8);
			}
			throw new ApplicationException("请求失败,http响应码:"+ response.getStatusLine().getStatusCode());
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
    }

    public static HttpClient getHttpClient() {
    	
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        
//        HttpHost localhost = new HttpHost("api.ucpaas.com", 8443);
//        cm.setMaxPerRoute(new HttpRoute(localhost), 5);
        return HttpClients.custom().setConnectionManager(cm).build();
    }
}
