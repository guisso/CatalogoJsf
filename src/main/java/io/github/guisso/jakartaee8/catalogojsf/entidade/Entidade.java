package io.github.guisso.jakartaee8.catalogojsf.entidade;

import java.util.Objects;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
public abstract class Entidade {

    private static Long id = 0L;
    private Long codigo;
    private String nome;
    private Boolean excluido;
    
    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Entidade() {
        nome = "[Indefinido]";
        codigo = ++id;
        excluido = false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="hashCode/equals">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entidade other = (Entidade) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
    //</editor-fold>    
}
