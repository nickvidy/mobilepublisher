
package com.vidy.fake.datamodel.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging {

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("perPage")
    @Expose
    private int perPage;
    @SerializedName("totalPages")
    @Expose
    private int totalPages;
    @SerializedName("currentPage")
    @Expose
    private int currentPage;
    @SerializedName("viewing")
    @Expose
    private int viewing;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getViewing() {
        return viewing;
    }

    public void setViewing(int viewing) {
        this.viewing = viewing;
    }

}
