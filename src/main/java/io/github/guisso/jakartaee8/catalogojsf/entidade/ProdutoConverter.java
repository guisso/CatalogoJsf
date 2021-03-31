package io.github.guisso.jakartaee8.catalogojsf.entidade;

import io.github.guisso.jakartaee8.catalogojsf.bean.CatalogoControllerBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter
        implements Converter<Produto> {

    @Override
    public Produto getAsObject(
            FacesContext context, 
            UIComponent component, 
            String value) {
        Produto produto = CatalogoControllerBean.localizarProduto(Long.parseLong(value));
        System.out.println("\r>> ProdutoConverter::getAsObject() = " + value + " => " + produto);
        return produto;
    }

    @Override
    public String getAsString(
            FacesContext context, 
            UIComponent component, 
            Produto value) {
        System.out.println("\r>> ProdutoConverter::getAsString() = " + value.getCodigo() + " => " + value);
        return value.getCodigo().toString();
    }

}
