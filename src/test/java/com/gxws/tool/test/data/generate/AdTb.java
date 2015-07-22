package com.gxws.tool.test.data.generate;

import java.math.BigDecimal;
import java.util.Date;

import com.gxws.tool.test.data.annotation.Random;
import com.gxws.tool.test.data.annotation.RandomDate;
import com.gxws.tool.test.data.annotation.RandomNumber;
import com.gxws.tool.test.data.annotation.RandomString;

/**
 * 广告
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
@Random(numberOfTime = 1000)
public class AdTb {
	@RandomString(length = 32)
	private String id;
	@RandomString(length = 50)
	private String url;
	@RandomDate(start = "2015/01/01 00:00:00", end = "2016/01/01 00:00:00")
	private Date time;
	@RandomNumber(start = "0", end = "1", decimalScale = 10)
	private BigDecimal sort;
	@RandomNumber(start = "0", end = "2")
	private Integer type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BigDecimal getSort() {
		return sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
