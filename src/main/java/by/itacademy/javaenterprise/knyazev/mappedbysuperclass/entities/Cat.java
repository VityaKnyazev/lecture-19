package by.itacademy.javaenterprise.knyazev.mappedbysuperclass.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "cats")
public class Cat extends Animal{
	@Enumerated(EnumType.STRING)	
	private Color color;
	@Column(nullable = false)
	private int age;
	
	public enum Color {
		GREEN,
		WHITE,
		BLACK,
		PINK
	}	
	
	@Override
	public String toString() {
		return "Cat [id=" + id + ", name=" + name + ", color=" + color.name() + ", age=" + age + "]";
	}
}
