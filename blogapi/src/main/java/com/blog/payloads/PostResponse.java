package com.blog.payloads;

import java.util.List;

public class PostResponse {

	private List<PostDto> postcontent;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalelement;
	private boolean islastpagte;

	public List<PostDto> getPostcontent() {
		return postcontent;
	}

	public void setPostcontent(List<PostDto> postcontent) {
		this.postcontent = postcontent;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalelement() {
		return totalelement;
	}

	public void setTotalelement(Long totalelement) {
		this.totalelement = totalelement;
	}

	public boolean isIslastpagte() {
		return islastpagte;
	}

	public void setIslastpagte(boolean islastpagte) {
		this.islastpagte = islastpagte;
	}

	public PostResponse(List<PostDto> postcontent, Integer pageNumber, Integer pageSize, Long totalelement,
			boolean islastpagte) {
		super();
		this.postcontent = postcontent;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalelement = totalelement;
		this.islastpagte = islastpagte;
	}

}
