package mmt.interview.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpService {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Async
	public void callAsyncService(String url)
	{
		HttpHeaders httpHeader = createHTTPHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeader);
		ResponseEntity<Integer> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Integer.class);
		} catch (RestClientException e) {
			//System.out.println(endTime.getTime()-startTime.getTime() +"\"***************\"");
		}
	}
	
	public void callSequentialService(String url)
	{
		HttpHeaders httpHeader = createHTTPHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeader);
        ResponseEntity<Integer> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Integer.class);
		} catch (RestClientException e) {
		}
	}
	
	 public static HttpHeaders createHTTPHeaders()
	    {
	        HttpHeaders headers = new HttpHeaders();
	        //headers.setContentType(MediaType.APPLICATION_JSON);
	        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
	        acceptableMediaTypes.add(MediaType.ALL);
	        headers.setAccept(acceptableMediaTypes);
	        return headers;
	    }
}
