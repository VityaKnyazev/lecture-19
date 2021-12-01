package by.itacademy.javaenterprise.knyazev.mappedbysuperclass.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "animals")
public class Dog extends Animal{
	@Column(name = "host_name", length=15, nullable = false)
	private String hostName;
	@Column(length=25, nullable = false)
	private String city;
}
