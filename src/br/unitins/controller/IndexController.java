package br.unitins.controller;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class IndexController implements Serializable {

    public void redirect(String target) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(target);
    }
}
