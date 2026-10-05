package br.com.unipds.leilao.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.unipds.leilao.util.JPATest;

public class LanceTest extends JPATest {

	@Test
	public void adicionaLance(){
		Item item = ItemTest.criaItem();
		manager.persist(item);

		Usuario usuario = UsuarioTest.criaUsuario();
		manager.persist(usuario);

		Lance lance = criaLance();
		lance.setItem(item);
		lance.setUsuario(usuario);

		manager.persist(lance);
		
		long num = manager.createQuery("select count(l) from Lance l", Long.class).getSingleResult();
		Assertions.assertEquals(1, num);
	}

	@Test
	public void alteraLance(){
		Lance lance = criaLance();
		manager.persist(lance);
		
		lance.setValor(new BigDecimal(2000));
		
		manager.flush();
		
		BigDecimal valor = manager.createQuery("select l.valor from Lance l where l = :lance", BigDecimal.class).setParameter("lance", lance).getSingleResult();
    Assertions.assertEquals(2000, valor.doubleValue(), 0.001);
	}
	
	@Test
	public void removeLance(){
		Lance lance = criaLance();
		manager.persist(lance);
		manager.remove(lance);
		
		long num = manager.createQuery("select count(l) from Lance l", Long.class).getSingleResult();
    Assertions.assertEquals(0, num);
	}
	
	@Test
	public void dataHoraNaoPodeSerNula(){
		Lance lance = criaLance();
		lance.setDataHora(null);

    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      manager.persist(lance);
    });
	}

	@Test
	public void dataHoraNaoPodeSerDoPassado(){
		Lance lance = criaLance();
		lance.setDataHora(new GregorianCalendar(1990, Calendar.JANUARY, 1));

    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      manager.persist(lance);
    });
  }

	//método auxiliar
	
	static Lance criaLance() {
		Lance lance = new Lance();
		lance.setValor(new BigDecimal(1000));
		lance.setVencedor(false);
		Calendar dataHoraAtual = Calendar.getInstance();
		dataHoraAtual.add(Calendar.HOUR, 1);
		lance.setDataHora(dataHoraAtual);
		return lance;
	}
	
}
