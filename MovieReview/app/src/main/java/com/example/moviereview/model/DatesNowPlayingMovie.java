package com.example.moviereview.model;

import com.google.gson.annotations.SerializedName;

public class DatesNowPlayingMovie {

	@SerializedName("maximum")
	private String maximum;

	@SerializedName("minimum")
	private String minimum;

	public String getMaximum(){
		return maximum;
	}

	public String getMinimum(){
		return minimum;
	}
}