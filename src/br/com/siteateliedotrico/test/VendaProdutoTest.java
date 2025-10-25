
package br.com.siteateliedotrico.test;

import br.com.siteateliedotrico.model.VendaProduto;
import br.com.siteateliedotrico.model.Produto;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaProdutoTest {
    
      @Test
    public void testCalculoSubtotal() {
        Produto p = new Produto();
        p.setPrecoVendaProduto(50.0);

        VendaProduto vp = new VendaProduto();
        vp.setProdutoId(p);
        vp.setQuantidade(2);

        double subtotalEsperado = 100.0;
        double subtotalCalculado = vp.getProdutoId().getPrecoVendaProduto() * vp.getQuantidade();

        assertEquals(subtotalEsperado, subtotalCalculado, 0.001);
    }
    
}