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


@WebServlet( name = "songs", value = "songs")
public class AudioResourceServlet extends HttpServlet {
    
	
	/*
	 * ConcurrentHashMap is thread safe; 
	 */
	ConcurrentHashMap<String, ConcurrentHashMap<String,String> > songDB = new ConcurrentHashMap<>();

	/*
	 * simply emulation of in memory database;  
	 */
	@Override
	 public void init() throws ServletException {


		ConcurrentHashMap<String,String> SongDetails = new ConcurrentHashMap<>();
        SongDetails.put("Artist","Artist");
		SongDetails.put("TrackTitle", "TrackTitle");
		SongDetails.put("AlbumTitle", "AlbumTitle");
        SongDetails.put("Tracknumber", "Tracknumber");
		SongDetails.put("Year", "Year");
		SongDetails.put("NumberOfReviews","NumberOfReviews");
        SongDetails.put("NumberOfCopiesSold","NumberOfCopiesSold");




         

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
		ConcurrentHashMap<String, String> Details = songDB.get(id);
		
		Audio songdetails = new Audio();
		songdetails.setId(id);
		songdetails.setArtistName(Details.get("Artist"));
        songdetails.setTrackTitle(Details.get("TrackTitle"));
        songdetails.setAlbumTitle(Details.get("AlbumTitle"));
        songdetails.setTrackNumber(Details.get("Tracknumber"));
        songdetails.setYear(Details.get("Year"));
        songdetails.setNumberOfReviews(Details.get("NumberOfReviews"));
        songdetails.setNumberOfCopiesSold(Details.get("NumberOfCopiesSold"));
    
		
	    Gson gson = new Gson();
	    
	    
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
        out.println("GET RESPONSE IN JSON - single element " + gson.toJson(songdetails));
        
        //out.println("GET RESPONSE IN JSON - all elements " + element.toString());
     
        out.flush();   
		 }
		 else{
			if( id== null){
				Gson gson = new Gson();
				PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println("GET RESPONSE IN JSON - all elements " + gson.toJson(songDB));
     
        out.flush();  

			}
			else{
			response.setStatus(400);

			PrintWriter out = response.getWriter();
        response.setContentType("application/string");
        response.setCharacterEncoding("UTF-8");
        
        out.println("Id doesnt exist in the database");


		 			 }			 } }
	
	

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	String id = request.getParameter("id");
		ConcurrentHashMap<String, String> Details = songDB.get(id);
        String artist = request.getParameter("artist");
        String tracktitle = request.getParameter("tracktitle");
        String albumtitle = request.getParameter("albumtitle");
        String tracknumber = request.getParameter("tracknumber");
        String year = request.getParameter("year");
        String numberofreviews = request.getParameter("numberofreviews");
        String numberofcopiessold = request.getParameter("numberofcopiessold");

        ConcurrentHashMap<String,String> songdetailstemp = new ConcurrentHashMap<String,String>();
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
	songdetailstemp.put("Artist",artist);}
else{
	songdetailstemp.put("Artist",Details.get("Artist"));
}
if(tracktitle!=null)
	{songdetailstemp.put("TrackTitle",tracktitle);}
else{

	songdetailstemp.put("TrackTitle",Details.get("TrackTitle"));
}

if(albumtitle!=null)
	{songdetailstemp.put("AlbumTitle",albumtitle);}
else{
	songdetailstemp.put("AlbumTitle",Details.get("AlbumTitle"));

}

if(tracknumber!=null)
	{songdetailstemp.put("Tracknumber",tracknumber);}
else{

	songdetailstemp.put("Tracknumber",Details.get("Tracknumber"));
}
if(year!=null)
	{songdetailstemp.put("Year",year);}
else{

		songdetailstemp.put("Year",Details.get("Year"));
}
if(numberofreviews!=null)
	{songdetailstemp.put("NumberOfReviews",numberofreviews);}
else{

		songdetailstemp.put("NumberOfReviews",Details.get("NumberOfReviews"));
}
if(numberofcopiessold!=null)
	{songdetailstemp.put("NumberOfCopiesSold",numberofcopiessold);}

else{

		songdetailstemp.put("NumberOfCopiesSold",Details.get("NumberOfCopiesSold"));
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
		response.getOutputStream().println("Data was notSend complete data");
	
	}
	else{

		songdetailstemp.put("Artist",artist);
		songdetailstemp.put("TrackTitle",tracktitle);
		songdetailstemp.put("AlbumTitle",albumtitle);
		songdetailstemp.put("Tracknumber",tracknumber);
		songdetailstemp.put("Year",year);
		songdetailstemp.put("NumberOfReviews",numberofreviews);
		songdetailstemp.put("NumberOfCopiesSold",numberofcopiessold);

		songDB.put(id, songdetailstemp);
	    response.setStatus(200);
	
	response.getOutputStream().println("POST RESPONSE: Artist " + songdetailstemp + " was added to the database.");






	}



	}
}
}
