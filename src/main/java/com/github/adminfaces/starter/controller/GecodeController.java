package com.github.adminfaces.starter.controller;

import okhttp3.OkHttpClient;


import okhttp3.Request;
import okhttp3.ResponseBody;
import com.github.adminfaces.starter.dto.GeocodeResult;


import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class GecodeController {

	// http://localhost:8080/SpringMVC/servlet/geocode
	  @RequestMapping(path = "/geocode", method = RequestMethod.GET )
	   public GeocodeResult getGeocode(@RequestParam String address) throws IOException {
	       OkHttpClient client = new OkHttpClient();

	       String encodedAddress = URLEncoder.encode(address, "UTF-8");

	       Request request = new Request.Builder()
	               .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
	               .get()
	               .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
	               .addHeader("x-rapidapi-key","121d05ba70mshe8d880b6f88d4d7p17dd28jsn01285487f57c"/*  Use your API Key here */)
	               .build();
	       
	       ResponseBody responseBody = client.newCall(request).execute().body();

	       ObjectMapper objectMapper = new ObjectMapper();
	       GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
	       return result;
	   }
}
