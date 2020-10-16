package com.example.moviereview.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseNowPlayingMovie {

	@SerializedName("dates")
	private DatesNowPlayingMovie dates;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<NowPlayingMovie> results;

	@SerializedName("total_results")
	private int totalResults;

	public DatesNowPlayingMovie getDates(){
		return dates;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<NowPlayingMovie> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}