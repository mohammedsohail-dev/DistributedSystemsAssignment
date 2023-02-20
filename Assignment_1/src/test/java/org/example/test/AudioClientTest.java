package org.example.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpResponse;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.example.model.Artist;
import org.example.model.Audio;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Random;



class get extends Thread{
@Override 
   public void run(){
    

    try {
        TestGet.testSongsGet();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }


   }


}


class post extends Thread{
    @Override 
       public void run(){
    
       
            try {
                TestPost.testSongsPost();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    
       }
    
    
    



class TestGet {

	public static void testSongsGet() throws Exception {

		String url = "http://localhost:9090/coen6317/songs";
		HttpClient client = new HttpClient();
        client.start();

        Request request = client.newRequest(url);
        request.param("id","id_1");
        ContentResponse response = request.send();
   

		assertThat(response.getStatus(), equalTo(200));
		
		String responseContent = IOUtils.toString(response.getContent());
		
		 System.out.println(responseContent);
		client.stop();
    
		
	}
}


class TestPost {

   
/// do get and do post for random gen data
    public static void testSongsPost() throws Exception {
		
		String url = "http://localhost:9090/coen6317/songs";
		HttpClient client = new HttpClient();
        client.start();
        
        Request request = client.POST(url);
        
        request.param("id","id_1");
        request.param("artist","artist");
        request.param("tracktitle","tracktitle");
		request.param("albumtitle","albumtitle");
		request.param("tracknumber","tracknumber");
		request.param("year","year");
		request.param("numberofreviews","numberofreviews");
		request.param("numberofcopiessold","numberofcopiessold");
        ContentResponse response = request.send();
		String res = new String(response.getContent());
		System.out.println(res);
		client.stop();

	
	
}


}



public class AudioClientTest{

    @Test
    public static void main(String[] args) throws Exception{


   // use for loop here instead
   for(int i=0;i<=60;i++){
     get gettest = new get();
     gettest.start();
   
     post posttest = new post();
     posttest.start();

   }

    
    


     



}




}

