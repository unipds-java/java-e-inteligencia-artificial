package br.com.unipds.leilao.util;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class JPATest {
	
	protected EntityManager manager;
	protected JPAUtil jpaUtil = new JPAUtil();

	@BeforeEach
	public void abreEntityManager(){
		manager = jpaUtil.getEntityManager();
		manager.getTransaction().begin();
	}

	
	@AfterEach
	public void fechaManager(){
		manager.getTransaction().rollback();
		manager.close();
	}

}
