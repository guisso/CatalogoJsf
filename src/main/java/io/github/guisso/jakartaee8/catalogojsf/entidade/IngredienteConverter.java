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
@FacesConverter(forClass = Ingrediente.class)
public class IngredienteConverter
        implements Converter<Ingrediente> {

    @Override
    public Ingrediente getAsObject(
            FacesContext context,
            UIComponent component,
            String value) {
        Ingrediente ingrediente = CatalogoControllerBean.localizarIngrediente(Long.parseLong(value));
        return ingrediente;
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Ingrediente value) {
        return value.getCodigo().toString();
    }

}
