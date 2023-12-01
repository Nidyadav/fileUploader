package com.example.springBoot.uploader.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "file")
public class FileCSV {
// @Id
// @GeneratedValue(strategy = GenerationType.AUTO)
// private long id;
	@Column
	private String source;
	@Column
	private String codeListCode;
	@Id
	@Column
	private String code;
	@Column
	private String displayValue;
	@Column
	private String LongDescription;
	@Column
	private String fromDate;
	@Column
	private String toDate;
	@Column
	private String sortingPriority;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCodeListCode() {
		return codeListCode;
	}

	public void setCodeListCode(String codeListCode) {
		this.codeListCode = codeListCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getLongDescription() {
		return LongDescription;
	}

	public void setLongDescription(String longDescription) {
		LongDescription = longDescription;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String localDate) {
		this.fromDate = localDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String localDate) {
		this.toDate = localDate;
	}

	public String getSortingPriority() {
		return sortingPriority;
	}

	public void setSortingPriority(String sortingPriority) {
		this.sortingPriority = sortingPriority;
	}

}
