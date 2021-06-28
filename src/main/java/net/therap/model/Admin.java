package net.therap.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author masud.rana
 * @since 28/6/21
 */
@Entity
@Table(name = "admin")
public class Admin extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 3, max = 30)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
