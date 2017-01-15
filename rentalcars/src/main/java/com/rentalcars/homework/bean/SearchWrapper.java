package com.rentalcars.homework.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Eva Balazsfalvi
 *
 * Represents a {@link Search} object wrapper.
 *
 */
public class SearchWrapper implements Serializable {
	private static final long serialVersionUID = -7754389957707145106L;

	@JsonProperty("Search")
	private Search search;
	
	public SearchWrapper(){}

	public SearchWrapper(Search search) {
		super();
		this.search = search;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((search == null) ? 0 : search.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchWrapper other = (SearchWrapper) obj;
		if (search == null) {
			if (other.search != null)
				return false;
		} else if (!search.equals(other.search))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchWrapper [search=").append(search).append("]");
		return builder.toString();
	}
}
