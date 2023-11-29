/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Utilisateur
 */
@Entity
public class Employes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public Employes() {
       
    }
    
    
 public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getLastName() {
        return lastName;
    }

    public Service getService() {
        return service;
    }
   
}

