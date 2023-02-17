package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.example.model.Audio;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

//@WebServlet(name = "skiiers", value = "skiiers")
@WebServlet( name = "songs", value = "songs")
public class AudioResourceServlet extends HttpServlet {
    
	
	/*
	 * ConcurrentHashMap is thread safe; 
	 */
	ConcurrentHashMap<String, ArrayList<String> > songDB = new ConcurrentHashMap<>();

	/*
	 * simply emulation of in memory database;  
	 */
	@Override
	 public void init() throws ServletException {


		ArrayList<String> SongDetails = new ArrayList<>();
        SongDetails.add("Artist");
		SongDetails.add("TrackTitle");
		SongDetails.add("AlbumTitle");
        SongDetails.add("Tracknumber");
		SongDetails.add("Year");
		SongDetails.add("NumberOfReviews");
        SongDetails.add("NumberOfCopiesSold");


         

		 songDB.put("id_1",SongDetails);
		 songDB.put("id_2",SongDetails);
		 songDB.put("id_3",SongDetails);
		 songDB.put("id_4",SongDetails);
		 songDB.put("id_5",SongDetails);
		
		 
	 }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String id = request.getParameter("id");


		List<String> idList = new ArrayList<>(songDB.keySet());

		
		 if(idList.contains(id))
		 {
		ArrayList<String> Details = songDB.get(id);
		
		Audio songdetails = new Audio();
		songdetails.setId(id);
		songdetails.setArtistName(Details.get(0));
        songdetails.setTrackTitle(Details.get(1));
        songdetails.setAlbumTitle(Details.get(2));
        songdetails.setTrackNumber(Details.get(3));
        songdetails.setYear(Details.get(4));
        songdetails.setNumberOfReviews(Details.get(5));
        songdetails.setNumberOfCopiesSold(Details.get(6));
    
		
	    Gson gson = new Gson();
	    JsonElement element = gson.toJsonTree(songDB);
	    
	    /*
	     * response in normal string message;
	     */
		//response.getOutputStream().println("Artist id is " + id +" name is " + name);
    
		
		/*
		 * response in json with as a data model
		 */
		
		 
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println("GET RESPONSE IN JSON - single element: " + gson.toJson(songdetails));
        
        out.println("GET RESPONSE IN JSON - all elements " + element.toString());
     
        out.flush();   
		 }
		 else{
			if( id== null){
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(songDB);
				PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println("GET RESPONSE IN JSON - single element: " + gson.toJson(songDB));
        
        out.println("GET RESPONSE IN JSON - all elements " + element.toString());
     
        out.flush();  

			}
			else{
			response.setStatus(400);

			PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        out.println("Id doesnt exist in the database");


			 } }
	
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	String id = request.getParameter("id");
		ArrayList<String> Details = songDB.get(id);
        String artist = request.getParameter("artist");
        String tracktitle = request.getParameter("tracktitle");
        String albumtitle = request.getParameter("albumtitle");
        String tracknumber = request.getParameter("tracknumber");
        String year = request.getParameter("year");
        String numberofreviews = request.getParameter("numberofreviews");
        String numberofcopiessold = request.getParameter("numberofcopiessold");

        ArrayList<String> songdetailstemp = new ArrayList<String>();
        List<String> idList = new ArrayList<>(songDB.keySet());
		if(idList.contains(id))
	{

    if( artist == null & tracktitle ==null & albumtitle==null & tracknumber == null & year== null & numberofreviews==null & numberofcopiessold==null)
	{
		response.setStatus(400);
		response.getOutputStream().println("No data sent");
	
	}

else
{
	if(artist!=null){
	songdetailstemp.add(artist);}
else{
	songdetailstemp.add(Details.get(0));
}
if(tracktitle!=null)
	{songdetailstemp.add(tracktitle);}
else{

	songdetailstemp.add(Details.get(1));
}

if(albumtitle!=null)
	{songdetailstemp.add(albumtitle);}
else{
	songdetailstemp.add(Details.get(2));

}

if(tracknumber!=null)
	{songdetailstemp.add(tracknumber);}
else{

	songdetailstemp.add(Details.get(3));
}
if(year!=null)
	{songdetailstemp.add(year);}
else{

		songdetailstemp.add(Details.get(4));
}
if(numberofreviews!=null)
	{songdetailstemp.add(numberofreviews);}
else{

		songdetailstemp.add(Details.get(5));
}
if(numberofcopiessold!=null)
	{songdetailstemp.add(numberofcopiessold);}

else{

		songdetailstemp.add(Details.get(6));
}    
	songDB.put(id, songdetailstemp);
	response.setStatus(200);
	
	response.getOutputStream().println("POST RESPONSE: Artist " + songdetailstemp + " is added to the database.");
}



}
else{


	if( artist == null | tracktitle ==null | albumtitle==null | tracknumber == null | year== null | numberofreviews==null | numberofcopiessold==null)
	{
		response.setStatus(400);
		response.getOutputStream().println("Send complete data");
	
	}
	else{

		songdetailstemp.add(artist);
		songdetailstemp.add(tracktitle);
		songdetailstemp.add(albumtitle);
		songdetailstemp.add(tracknumber);
		songdetailstemp.add(year);
		songdetailstemp.add(numberofreviews);
		songdetailstemp.add(numberofcopiessold);

		songDB.put(id, songdetailstemp);
	    response.setStatus(200);
	
	response.getOutputStream().println("POST RESPONSE: Artist " + songdetailstemp + " is added to the database.");






	}



	}
}
}

