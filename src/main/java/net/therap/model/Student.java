package net.therap.model;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author masud.rana
 * @since 28/6/21
 */
@Entity
@Table(name = "student")
@NamedQuery(name = "findAllStudents", query = "SELECT s from Student s")
public class Student extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 3, max = 15, message = "Contacts should be valid")
    private String contactNo;

    @Lob
    private byte[] image;

    @Transient
    private MultipartFile imageFile;

    @Transient
    private String base64image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setData(MultipartFile file) throws IOException {
        this.image = file.getBytes();
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getBase64image() {
        return base64image;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }
}
