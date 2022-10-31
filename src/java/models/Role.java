/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author tobys
 */
public class Role implements Serializable{
    private int roleId;
    private String rolename; 

    public Role() {
    }

    public Role(int roleId, String rolename) {
        this.roleId = roleId;
        this.rolename = rolename;
    }
    

    public int getRoleId() {
        return roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
}
