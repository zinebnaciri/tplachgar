package service;

import entities.Employes;

public class EmployesService extends AbstractFacade<Employes> {

    @Override
    protected Class<Employes> getEntityClass() {
        return Employes.class;
    }

}
