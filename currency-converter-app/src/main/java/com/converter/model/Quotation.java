package com.converter.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="QUOTATION", schema="public")
public class Quotation {

	@Id
	@GeneratedValue(generator="seq_id_quotation", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_id_quotation", sequenceName="seq_id_quotation")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_fk")
	private User user;
	
	private String source;
	private String destination;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date time;
	private long amount;
	private double result;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
}
