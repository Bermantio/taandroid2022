package com.example.lazismu.retrofit.response;

import java.util.List;

import com.google.firebase.database.core.Repo;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("data")
	private List<ReportData> data;

	@SerializedName("last_page")
	private int lastPage;

	@SerializedName("next_page_url")
	private String nextPageUrl;

	@SerializedName("prev_page_url")
	private Object prevPageUrl;

	@SerializedName("first_page_url")
	private String firstPageUrl;

	@SerializedName("path")
	private String path;

	@SerializedName("total")
	private int total;

	@SerializedName("last_page_url")
	private String lastPageUrl;

	@SerializedName("from")
	private int from;

	@SerializedName("links")
	private List<LinksItem> links;

	@SerializedName("to")
	private int to;

	@SerializedName("current_page")
	private int currentPage;

	public int getPerPage(){
		return perPage;
	}

	public List<ReportData> getData(){
		return data;
	}

	public int getLastPage(){
		return lastPage;
	}

	public String getNextPageUrl(){
		return nextPageUrl;
	}

	public Object getPrevPageUrl(){
		return prevPageUrl;
	}

	public String getFirstPageUrl(){
		return firstPageUrl;
	}

	public String getPath(){
		return path;
	}

	public int getTotal(){
		return total;
	}

	public String getLastPageUrl(){
		return lastPageUrl;
	}

	public int getFrom(){
		return from;
	}

	public List<LinksItem> getLinks(){
		return links;
	}

	public int getTo(){
		return to;
	}

	public int getCurrentPage(){
		return currentPage;
	}
}