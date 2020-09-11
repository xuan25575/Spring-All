package com.springboot.pojo;

import java.io.Serializable;

public class Permission implements Serializable {

	private static final long serialVersionUID = 7160557680614732403L;
	private Integer id;
	private String url;
	private String urlDesc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the urlDesc
	 */
	public String getUrlDesc() {
		return urlDesc;
	}

	/**
	 * @param urlDesc the urlDesc to set
	 */
	public void setUrlDesc(String urlDesc) {
		this.urlDesc = urlDesc;
	}
}
