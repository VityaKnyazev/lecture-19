package by.itacademy.javaenterprise.knyazev.singletable.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "POEM")
@NamedNativeQuery(name = "allPoemWriters", query = "SELECT pw FROM writers pw")
public class PoemWriter extends Writer{
	@Column(name = "poem_name", length = 30)
	private String poemName;	
	
	@Column(name = "page_count")
	private int pageCount;
}
