package dataAccess.entity;

/**
 * Created by Dotin school 5 on 8/6/2016.
 */

public class Customer {
    private int id;
    private String customerNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}