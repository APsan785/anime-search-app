package com.apsan.retrofit.APIPackage;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryFetched {

    @SerializedName("request_hash")
    @Expose
    private String requestHash;
    @SerializedName("request_cached")
    @Expose
    private Boolean requestCached;
    @SerializedName("request_cache_expiry")
    @Expose
    private Integer requestCacheExpiry;
    @SerializedName("results")
    @Expose
    private List<AnimeObject> results = null;
    @SerializedName("last_page")
    @Expose
    private Integer lastPage;

    public String getRequestHash() {
        return requestHash;
    }

    public void setRequestHash(String requestHash) {
        this.requestHash = requestHash;
    }

    public Boolean getRequestCached() {
        return requestCached;
    }

    public void setRequestCached(Boolean requestCached) {
        this.requestCached = requestCached;
    }

    public Integer getRequestCacheExpiry() {
        return requestCacheExpiry;
    }

    public void setRequestCacheExpiry(Integer requestCacheExpiry) {
        this.requestCacheExpiry = requestCacheExpiry;
    }

    public List<AnimeObject> getResults() {
        return results;
    }

    public void setResults(List<AnimeObject> results) {
        this.results = results;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

}
