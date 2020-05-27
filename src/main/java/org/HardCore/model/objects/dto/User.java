package org.HardCore.model.objects.dto;

import org.HardCore.model.dao.RoleDAO;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String vorname = "Student";
    private String name = "Unternehmen";
    private String email;
    private String password;
    private List<Role> roles = null;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String id) {
        this.email = id;
    }

    public boolean hasRole(String role) {
        if (this.roles == null) {
            getRoles();
        }
        for(Role r : roles) {
            if (r.getBezeichnung().equals(role)) return true;
        }
        return false;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void getRoles() {
        this.roles = RoleDAO.getInstance().getRolesForUser(this);
    }
}
