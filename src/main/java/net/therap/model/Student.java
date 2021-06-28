package net.therap.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author masud.rana
 * @since 28/6/21
 */
@Entity
@Table(name = "student")
public class Student extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
