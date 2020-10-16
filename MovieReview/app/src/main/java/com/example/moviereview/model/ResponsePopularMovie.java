package com.example.moviereview.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePopularMovie{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<PopularMovie> results;

	@SerializedName("total_results")
	private int totalResults;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<PopularMovie> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}