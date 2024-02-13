package it21678.application.entity;

import jakarta.persistence.*;
import org.hibernate.mapping.Value;

import java.util.List;

@Entity
@Table(name="forms")
public class Form {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @Column
    private String members;
    @Column
    private String purpose;
    @Column
    private String statute;
    @Column
    private String address;
    @Column
    private String result = "In progress";

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name = "user_forms",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames={"user_id", "form_id"})})
    private List<User> users;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatute() {
        return statute;
    }

    public void setStatute(String statute) {
        this.statute = statute;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public Form(Integer id, String members, String purpose, String statute, String address, String result) {
        this.id = id;
        this.members = members;
        this.purpose = purpose;
        this.statute = statute;
        this.address = address;
        this.result = result;
    }

    public Form(){

    }

    @Override
    public String toString() {
        return "Form{" +
                "Id=" + id +
                ", members='" + members + '\'' +
                ", purpose='" + purpose + '\'' +
                ", address='" + address + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

