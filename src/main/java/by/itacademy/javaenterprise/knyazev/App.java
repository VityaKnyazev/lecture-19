package by.itacademy.javaenterprise.knyazev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.dao.exceptions.DAOException;
import by.itacademy.javaenterprise.knyazev.jpa.JpaUtil;
import by.itacademy.javaenterprise.knyazev.mappedbysuperclass.dao.CatDAO;
import by.itacademy.javaenterprise.knyazev.mappedbysuperclass.entities.Cat;
import by.itacademy.javaenterprise.knyazev.singletable.dao.PoemWriterDAO;
import by.itacademy.javaenterprise.knyazev.singletable.entities.PoemWriter;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		CatDAO catDAO = new CatDAO(JpaUtil.getEntityManager());
		try {
			Cat cat = catDAO.find(2L);
			logger.info(cat.toString());
		} catch (DAOException e) {
			logger.error(e.getMessage());
		}
		
		PoemWriter pw = new PoemWriter();
		
		pw.setName("Никон");
		pw.setAge(59);
		pw.setPoemName("The tell about The Truth");
		pw.setPageCount(530);
		
		PoemWriterDAO pwDAO = new PoemWriterDAO(JpaUtil.getEntityManager());
		try {
			pwDAO.save(pw);
		} catch (DAOException e) {
			logger.error(e.getMessage());
		}
		
		try {
			logger.info("Poem author:" + pwDAO.find(3L).getName());
		} catch (DAOException e) {
			logger.error(e.getMessage());
		}
		
		JpaUtil.closeEntityManagerFactory();
	}
}
