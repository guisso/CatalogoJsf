package io.github.guisso.jakartaee8.catalogojsf.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
public class Produto
        extends Entidade {

    private BigDecimal custo; // Custo base: R$ 10,00
    private BigDecimal lucro;
    private BigDecimal preco;
    private List<Ingrediente> ingredientes;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Produto() {
        custo = BigDecimal.TEN;
        lucro = BigDecimal.ZERO;
        preco = BigDecimal.ZERO;
        ingredientes = new ArrayList<>();
    }

    public Produto(
            String nome,
            BigDecimal lucro,
            BigDecimal preco) {
        this();
        setNome(nome);

        // Prioriza definição de preço em detrimento
        // da definição do lucro
        if (preco != null) {
            setPreco(preco);
        } else {
            setLucro(lucro);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        // Prioriza o ajuste do lucro 
        // se há um preço definido
        System.out.println(">> Preço: " + preco);
        if (custo != null && preco != null
                && custo.compareTo(BigDecimal.ZERO) != 0) {
            lucro = preco.subtract(custo).divide(custo,
                    RoundingMode.HALF_UP);
        } else if (custo != null && lucro != null
                && (preco == null
                || preco.equals(BigDecimal.ZERO))) {
            preco = lucro.plus().multiply(custo);
        }
        this.custo = custo;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public void setLucro(BigDecimal lucro) {
        if (lucro != null && custo != null) {
            preco = lucro.plus().multiply(custo);
        }
        this.lucro = lucro;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        if (preco != null && custo != null
                && custo.compareTo(BigDecimal.ZERO) != 0) {
            lucro = preco.subtract(custo).divide(custo,
                    RoundingMode.HALF_UP);
        }
        this.preco = preco;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        if (ingredientes.isEmpty()) {
            setCusto(BigDecimal.TEN);
        } else {
            BigDecimal custoTotal = ingredientes
                    .stream()
                    .map(x -> x.getCusto())
                    .reduce(BigDecimal.ZERO,
                            BigDecimal::add);
            setCusto(custoTotal.add(BigDecimal.TEN));
        }

        this.ingredientes = ingredientes;
    }
    //</editor-fold>

    public void addIngrediente(Ingrediente ingrediente) {
        setCusto(getCusto().add(ingrediente.getCusto()));
        ingredientes.add(ingrediente);
    }

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString() {
        return getNome()
                + " >> " + ingredientes;
    }
    //</editor-fold>

}
