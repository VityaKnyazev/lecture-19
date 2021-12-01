package by.itacademy.javaenterprise.knyazev.singletable.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.dao.AbstractDAO;
import by.itacademy.javaenterprise.knyazev.dao.exceptions.DAOException;
import by.itacademy.javaenterprise.knyazev.singletable.entities.PoemWriter;

public class PoemWriterDAO extends AbstractDAO<PoemWriter>{
	Logger logger = LoggerFactory.getLogger(PoemWriterDAO.class);

	public PoemWriterDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Long save(PoemWriter poemWriter) throws DAOException {
		if (poemWriter != null) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(poemWriter);
				entityManager.getTransaction().commit();
				return poemWriter.getId();
			} catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
				entityManager.getTransaction().rollback();
				logger.error("Transaction on method int save(PoemWriter poemWriter) failed: " + e.getMessage()
						+ "with name of class exception: " + e.getClass().getCanonicalName(), e);
				return null;
			}
		} else {
			throw new DAOException(
					"Expected PoemWriter object. Null was given in method Long save(PoemWriter poemWriter)");
		}
	}

	@Override
	public PoemWriter find(Long id) throws DAOException {

		if (id == null || id < 0L) {
			throw new DAOException(
					"Expected PoemWriter object. Null or bad ID was given in method PoemWriter find(Long id)");
		}

		try {
			return entityManager.find(PoemWriter.class, id);
		} catch (IllegalArgumentException e) {
			logger.error("Can't get PoemWriter object on id=" + id + " on method PoemWriter find(Integer id): "
					+ e.getMessage() + " with exception class name: " + e.getClass().getCanonicalName(), e);
		}

		return null;
	}

	@Override
	public List<PoemWriter> findAll() {

		try {
			return entityManager.createNamedQuery("allPoemWriters", PoemWriter.class).getResultList();
		} catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
			logger.error("Error in method List<PoemWriter> findAll(): " + e.getMessage() + " from class exception name: "
					+ e.getClass().getCanonicalName(), e);
		}

		return Collections.emptyList();
	}

	@Override
	public void update(PoemWriter PoemWriter) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(PoemWriter);
			entityManager.getTransaction().commit();
		} catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
			entityManager.getTransaction().rollback();
			logger.error("Error in method update(PoemWriter poemWriter): " + e.getMessage() + " from class exception name: "
					+ e.getClass().getCanonicalName());
			throw new DAOException("Error can't merge object on method void update(PoemWriter poemWriter)", e);
		}
	}

	@Override
	public void delete(PoemWriter PoemWriter) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(PoemWriter);
			entityManager.getTransaction().commit();
		} catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
			entityManager.getTransaction().rollback();
			logger.error("Error in method void delete(PoemWriter poemWriter): " + e.getMessage()
					+ " from class exception name: " + e.getClass().getCanonicalName());
			throw new DAOException("Error can't remove object on method void delete(PoemWriter poemWriter)", e);
		}
	}

}
