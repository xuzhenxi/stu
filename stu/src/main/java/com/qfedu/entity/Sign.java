package com.qfedu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Sign {
    private Integer id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date todaydate;
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date signdate;
    private Integer lateflag;
    private Integer amflag;
    private String uno;
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date enddate;
    
    private User user;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(Date todaydate) {
        this.todaydate = todaydate;
    }

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public Integer getLateflag() {
        return lateflag;
    }

    public void setLateflag(Integer lateflag) {
        this.lateflag = lateflag;
    }

    public Integer getAmflag() {
        return amflag;
    }

    public void setAmflag(Integer amflag) {
        this.amflag = amflag;
    }
}