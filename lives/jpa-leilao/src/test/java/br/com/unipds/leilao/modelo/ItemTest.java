package br.com.unipds.leilao.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import jakarta.validation.ConstraintViolationException;

import br.com.unipds.leilao.util.JPATest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest extends JPATest {

  @Test
  public void adicionaItem() {
    Item item = criaItem();

    manager.persist(item);

    long num = manager.createQuery("select count(i) from Item i", Long.class).getSingleResult();
    Assertions.assertEquals(1, num);
  }

  @Test
  public void alteraItem() {
    Item item = criaItem();
    manager.persist(item);

    item.aumentaPreco(new BigDecimal(1000));

    manager.flush();

    BigDecimal preco = manager.createQuery("select i.precoInicial from Item i where i = :item", BigDecimal.class).setParameter("item", item).getSingleResult();
    Assertions.assertEquals(6000, preco.doubleValue(), 0.001);
  }

  @Test
  public void removeItem() {
    Item item = criaItem();
    manager.persist(item);
    manager.remove(item);

    long num = manager.createQuery("select count(i) from Item i", Long.class).getSingleResult();
    Assertions.assertEquals(0, num);
  }

  @Test
  public void adicionaItemELeilaoJuntos() {
    Item item = criaItem();
    Leilao leilao = criaLeilao();
    item.setLeilao(leilao);

    manager.persist(item);
    manager.remove(item);

    long num = manager.createQuery("select count(i) from Item i", Long.class).getSingleResult();
    Assertions.assertEquals(0, num);
  }

  @Test
  public void adicionaCategoriasNosItem() {
    Item item = criaItem();
    item.adicionaCategoria("mobile");
    item.adicionaCategoria("premium");

    manager.persist(item);

    long num = manager.createQuery("select i from Item i where i = :item", Item.class).setParameter("item", item).getSingleResult().getCategorias().size();
    Assertions.assertEquals(2, num);
  }

  @Test
  public void nomeDoItemNaoPodeSerNulo() {
    Item item = criaItem();
    item.setNome(null);

    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      manager.persist(item);
    });
  }

  @Test
  public void nomeDoItemNaoPodeSerMuitoPequeno() {
    Item item = criaItem();
    item.setNome("luva"); //no minimo 5 caracteres

    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      manager.persist(item);
    });
  }

  @Test
  public void temNamedQueryQueContaTotalDeItensCadastrados() {
    long num = manager.createNamedQuery("Item.contaTotalDeItensCadastrados", Long.class).getSingleResult();
    Assertions.assertEquals(0, num);
  }

  //métodos auxiliares

  static Item criaItem() {
    Item item = new Item();
    item.setNome("Iphone 4k Plus Master");
    item.setPrecoInicial(new BigDecimal(5000));
    return item;
  }

  static Leilao criaLeilao() {
    Leilao leilao = new Leilao();
    leilao.setCodigo("XPTO-KKK");
    leilao.setDataHoraInicio(new GregorianCalendar(2016, Calendar.MAY, 9));
    leilao.setDataHoraFim(new GregorianCalendar(2016, Calendar.MAY, 10));
    return leilao;
  }

}
