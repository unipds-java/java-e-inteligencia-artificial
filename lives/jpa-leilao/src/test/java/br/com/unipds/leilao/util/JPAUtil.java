package br.com.unipds.leilao.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("leilao");

	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public void close() {
		factory.close();
	}
}