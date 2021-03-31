package io.github.guisso.jakartaee8.catalogojsf.entidade;

import java.math.BigDecimal;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
public class Ingrediente
        extends Entidade {

    private BigDecimal custo;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Ingrediente() {
        custo = BigDecimal.ZERO;
    }

    public Ingrediente(String nome, BigDecimal custo) {
        setNome(nome);
        this.custo = custo;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString() {
        return getNome();
    }
    //</editor-fold>

}
