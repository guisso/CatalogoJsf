package io.github.guisso.jakartaee8.catalogojsf.bean;

import io.github.guisso.jakartaee8.catalogojsf.entidade.Ingrediente;
import io.github.guisso.jakartaee8.catalogojsf.entidade.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
@Named
@SessionScoped
public class CatalogoControllerBean
        implements Serializable {

    private static List<Produto> produtos;
    private static List<Ingrediente> ingredientes;
    private Produto produtoSelecionado;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        CatalogoControllerBean.produtos = produtos;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        CatalogoControllerBean.ingredientes = ingredientes;
    }
    //</editor-fold>

    public CatalogoControllerBean() {
        if (produtoSelecionado == null
                && produtos != null) {
            produtoSelecionado = produtos.get(0);
            System.out.println("\r>> Construtor()::ProdutoSelecionado: "
                    + produtoSelecionado);
        }
    }

    @PostConstruct
    public void init() {

        System.out.println("\r>> CatalogoController::init()");

        if (produtos == null) {
            produtos = new ArrayList<>();

            produtos.add(new Produto("Mussarela",
                    null, new BigDecimal("33.99")));
            produtos.add(new Produto("Calabreza",
                    null, new BigDecimal("34.99")));
            produtos.add(new Produto("À Moda",
                    null, new BigDecimal("35.99")));
            produtos.add(new Produto("Pepperoni",
                    null, new BigDecimal("36.99")));
            produtos.add(new Produto("Marguerita",
                    null, new BigDecimal("37.99")));
            produtos.add(new Produto("Rapadura",
                    null, new BigDecimal("53.99")));
            produtos.add(new Produto("Quatro queijos",
                    null, new BigDecimal("25.99")));

            System.out.println("\r>> Produtos:\r   "
                    + produtos);

        }

        if (ingredientes == null) {
            ingredientes = new ArrayList<>();

            ingredientes.add(new Ingrediente("Tomate",
                    new BigDecimal("2.75")));
            ingredientes.add(new Ingrediente("Pimentão",
                    new BigDecimal("3.5")));
            ingredientes.add(new Ingrediente("Cebola",
                    new BigDecimal("2.5")));
            ingredientes.add(new Ingrediente("Mussarela",
                    new BigDecimal("5.75")));
            ingredientes.add(new Ingrediente("Presunto",
                    new BigDecimal("6.15")));
            ingredientes.add(new Ingrediente("Calabreza",
                    new BigDecimal("5.5")));
            ingredientes.add(new Ingrediente("Molho",
                    new BigDecimal("3.5")));
            ingredientes.add(new Ingrediente("Majericão",
                    new BigDecimal("3.5")));
            ingredientes.add(new Ingrediente("Pepperoni",
                    new BigDecimal("7.5")));
            ingredientes.add(new Ingrediente("Rapadura",
                    new BigDecimal("2.5")));

            System.out.println("\r>> Ingredientes:\r   "
                    + ingredientes);
        }

        if (produtoSelecionado == null) {
            produtoSelecionado = produtos.get(0);
            System.out.println("\r>> init()::ProdutoSelecionado: "
                    + produtoSelecionado);
        }

    }

    public void onIngredientesSelecionados(ValueChangeEvent vce) {
//        String idComponente = vce.getComponent().getId();
//        if (idComponente.equals("ingredienteList")) {
            
            Object oldValue = vce.getOldValue();
            System.out.println("\r>> I/OLD: " + oldValue);
            Object newValue = vce.getNewValue();
            System.out.println("\r>> I/NEW: " + newValue);

            System.out.println("\r>> onIngredientesSelecionados()::Produto selecionado:"
                    + produtoSelecionado.getNome());
            System.out.println("\r>> onIngredientesSelecionados::Ingredientes selecionados:"
                    + (List<Ingrediente>) newValue);

            if (newValue != null) {
                produtoSelecionado.setIngredientes((List<Ingrediente>) newValue);
                // Mantém o produto selecionado 
                // no topo da lista de produtos
                produtos.remove(produtoSelecionado);
                produtos.add(0, produtoSelecionado);
            }
            
//        }
    }

    public static Produto localizarProduto(Long codigo) {
        return produtos
                .stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findAny()
                .orElse(null);
    }

    public static Ingrediente localizarIngrediente(Long codigo) {
        return ingredientes
                .stream()
                .filter(i -> i.getCodigo().equals(codigo))
                .findAny()
                .orElse(null);
    }

    public void onProdutoSelecionadoAjax() {
        System.out.println(">> AJAX: "
                + produtoSelecionado
                + " >> "
                + produtoSelecionado.getIngredientes());
    }

}
