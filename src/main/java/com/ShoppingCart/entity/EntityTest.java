package com.ShoppingCart.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="SERVICES_INSTANCES")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="instanceId")
public class EntityTest implements Serializable{

	@Id
	@Column(name="INSTANCE_ID", unique = true, nullable = false)
	private int instanceId;
	
	@Column(name = "INSTANCE_NAME", nullable = true)
	private String instanceName;
	
	@Column(name = "REBOOT_DATE", nullable = true)
	private Timestamp rebootDate;
	
	@Column(name = "SERVICE_NAME" , nullable = true)
	private String serviceName;

	public EntityTest() {
	}
	
	public EntityTest(int userId, String instanceName, Timestamp rebootDate,
			String serviceName) {
		this.instanceId = userId;
		this.instanceName = instanceName;
		
		this.rebootDate = rebootDate;
		this.serviceName = serviceName;
	
	}
	
	public int getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public void setRebootDate(Timestamp rebootDate) {
		this.rebootDate = rebootDate;
	}
	
	public Timestamp getRebootDate() {
		return rebootDate;
	}

}
