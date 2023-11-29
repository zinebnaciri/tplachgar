package converters;

import entities.Service;
import service.ServiceService;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean
@FacesConverter("serviceConverter")
public class ServiceConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        ServiceService serviceService = new ServiceService();
        
        try {
            Integer id = Integer.valueOf(value);
            return serviceService.getById(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof Service) {
            return String.valueOf(((Service) value).getId());
        } else {
            throw new IllegalArgumentException("Invalid type: " + value.getClass());
        }
    }
}
