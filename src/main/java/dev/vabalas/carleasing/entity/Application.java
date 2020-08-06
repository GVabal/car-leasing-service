package dev.vabalas.carleasing.entity;

import javax.persistence.*;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "credit_amount", nullable = false)
    private int creditAmount;

    @Column(name = "approved")
    private boolean approved;

    protected Application() {}

    public Application(Car car, Person person, int creditAmount) {
        this.car = car;
        this.person = person;
        this.creditAmount = creditAmount;
        this.approved = processCreditApplication();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int amount) {
        this.creditAmount = amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Credit application will result as successfully approved when monthly income is not lower
     * than 600eur in family per person, otherwise as rejected.
     * @return boolean value whether loan is approved or not
     */
    private boolean processCreditApplication() {
        return person.getMonthlyIncome() >= 600 * (person.getDependents() + 1);
    }
}
