package com.github.bilak.apptemplate.jsf.converter;

import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

@Component("CharArrayConverter")
public class CharArrayConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) {
        if (newValue == null) {
            return newValue;
        }
        return newValue.toCharArray();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        char[] inputValue;
        try {
            inputValue = (char[]) value;
        } catch (ClassCastException ccex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Object was not present in the desired format", null);
            throw new ConverterException(message);
        }
        return new String(inputValue);
    }

}
