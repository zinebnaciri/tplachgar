package domaines;

import entities.Employes;
import entities.Service;
import service.EmployesService;
import service.ServiceService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class EmployesBean {

    private Employes employes;
    private Employes selectedEmployee;
    private List<Employes> employees;
    private List<Service> services;
    private EmployesService employesService;
    private ServiceService serviceService;
    private String selectedServiceId;


public String getSelectedServiceId() {
    return selectedServiceId;
}

public void setSelectedServiceId(String selectedServiceId) {
    this.selectedServiceId = selectedServiceId;
}

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

    // Update the onCreateAction method to handle potential exceptions more clearly
public void onCreateAction() {
    try {
        // Set the selected service for the employee
        Service selectedService = serviceService.getById(Integer.parseInt(selectedServiceId));
        employes.setService(selectedService);

        // Create the employee
        employesService.create(employes);

        employes = new Employes();
        employees = null; // Reset the list to trigger a reload
        System.out.println("Employee created successfully!");
    } catch (Exception e) {
        System.err.println("Error creating employee: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating employee", e.getMessage()));
    }
}


    public void onEditAction(Employes emp) {
        selectedEmployee = employesService.getById(emp.getId());
    }
public void onRowSelect(SelectEvent event) {
    selectedEmployee = (Employes) event.getObject();

    // Populate input fields with selected employee data
    employes.setFirstName(selectedEmployee.getFirstName());
    employes.setLastName(selectedEmployee.getLastName());
    employes.setService(selectedEmployee.getService());
}


 public void onUpdateAction() {
    try {
        if (selectedEmployee != null) {
            // Update selected employee with modified data
            selectedEmployee.setFirstName(employes.getFirstName());
            selectedEmployee.setLastName(employes.getLastName());
            selectedEmployee.setService(employes.getService());

            employesService.update(selectedEmployee);
            employees = null; // Reset the list to trigger a reload
            selectedEmployee = null; // Clear the selected employee
            System.out.println("Employee updated successfully!");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No employee selected", "Please select an employee to update."));
        }
    } catch (Exception e) {
        System.err.println("Error updating employee: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating employee", e.getMessage()));
    }
}



   public void onDeleteAction() {
   
        if (selectedEmployee != null) {
            employesService.delete(selectedEmployee);
            employees = null; 
            selectedEmployee = null; 
          
        } 
}

}
