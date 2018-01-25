package online.work.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by 84825 on 2018/1/24.
 */

@Entity
@Table(name = "tb_contact")
public class ContactModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String mobile;
    @Column
    private String address;
    @Column
    private String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ContactModel(String name, String email, String mobile, String address, String position) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.position = position;
    }
    public ContactModel(Long id, String name, String email, String mobile, String address, String position) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.position = position;
    }
    public ContactModel(Long id) {
        this.id=id;
    }
    public ContactModel() {
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
