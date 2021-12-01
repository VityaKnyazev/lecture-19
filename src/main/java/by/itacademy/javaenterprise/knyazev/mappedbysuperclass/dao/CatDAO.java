package by.itacademy.javaenterprise.knyazev.mappedbysuperclass.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.dao.AbstractDAO;
import by.itacademy.javaenterprise.knyazev.dao.exceptions.DAOException;
import by.itacademy.javaenterprise.knyazev.mappedbysuperclass.entities.Cat;

public class CatDAO extends AbstractDAO<Cat>{
	Logger logger = LoggerFactory.getLogger(CatDAO.class);

	public CatDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Long save(Cat cat) throws DAOException {
		if (cat != null) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(cat);
				entityManager.getTransaction().commit();
				return cat.getId();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				logger.error("Transaction on method int save(Cat Cat) failed: " + e.getMessage()
						+ "with name of class exception: " + e.getClass().getCanonicalName(), e);
				return null;
			}
		} else {
			throw new DAOException(
					"Expected Cat object. Null was given in method Long save(Cat cat)");
		}
	}

	@Override
	public Cat find(Long id) throws DAOException {

		if (id == null || id < 0L) {
			throw new DAOException(
					"Expected Cat object. Null or bad ID was given in method Cat find(Long id)");
		}

		try {
			return entityManager.find(Cat.class, id);
		} catch (IllegalArgumentException e) {
			logger.error("Can't get Cat object on id=" + id + " on method Cat find(Integer id): "
					+ e.getMessage() + " with exception class name: " + e.getClass().getCanonicalName(), e);
		}

		return null;
	}

	@Override
	public List<Cat> findAll() {

		try {
			return entityManager.createNamedQuery("Select c From animals", Cat.class).getResultList();
		} catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
			logger.error("Error in method List<Cat> findAll(): " + e.getMessage() + " from class exception name: "
					+ e.getClass().getCanonicalName(), e);
		}

		return Collections.emptyList();
	}

	@Override
	public void update(Cat cat) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(cat);
			entityManager.getTransaction().commit();
		} catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
			entityManager.getTransaction().rollback();
			logger.error("Error in method update(Cat Cat): " + e.getMessage() + " from class exception name: "
					+ e.getClass().getCanonicalName());
			throw new DAOException("Error can't merge object on method void update(Cat Cat)", e);
		}
	}

	@Override
	public void delete(Cat cat) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(cat);
			entityManager.getTransaction().commit();
		} catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
			entityManager.getTransaction().rollback();
			logger.error("Error in method void delete(Cat cat): " + e.getMessage()
					+ " from class exception name: " + e.getClass().getCanonicalName());
			throw new DAOException("Error can't remove object on method void delete(Cat cat)", e);
		}
	}

}
