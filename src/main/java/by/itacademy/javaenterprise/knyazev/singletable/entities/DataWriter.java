package by.itacademy.javaenterprise.knyazev.singletable.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DATA")
public class DataWriter extends Writer{
	@Column(length = 1024)
	private String data;
}
