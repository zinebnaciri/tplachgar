package domaines;

import entities.Employes;
import entities.Service;
import service.EmployesService;
import service.ServiceService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class EmployesBean {

    private Employes employes;
    private Employes selectedEmployee;
    private List<Employes> employees;
    private List<Service> services;
    private EmployesService employesService;
    private ServiceService serviceService;

    public EmployesBean() {
        employes = new Employes();
        selectedEmployee = new Employes();
        employesService = new EmployesService();
        serviceService = new ServiceService();
    }

    public Employes getEmployes() {
        return employes;
    }

    public void setEmployes(Employes employes) {
        this.employes = employes;
    }

    public List<Employes> getEmployees() {
        if (employees == null)
            employees = employesService.getAll();
        return employees;
    }

    public void setEmployees(List<Employes> employees) {
        this.employees = employees;
    }

    public List<Service> getServices() {
        if (services == null)
            services = serviceService.getAll();
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Employes getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employes selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public void onCreateAction() {
        try {
            employesService.create(employes);
            employes = new Employes();
            employees = null; // Reset the list to trigger a reload
            System.out.println("Employee created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating employee: " + e.getMessage());
        }
    }

    public void onEditAction(Employes emp) {
        selectedEmployee = employesService.getById(emp.getId());
    }

    public void onUpdateAction() {
        employesService.update(selectedEmployee);
        employees = null; // Reset the list to trigger a reload
    }

    public void onDeleteAction(Employes emp) {
        employesService.delete(emp);
        employees = null; // Reset the list to trigger a reload
    }
}
