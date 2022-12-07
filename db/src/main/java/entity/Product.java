package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Product {
    @JsonIgnore
    private int id;
    @JsonProperty("product_name")
    private String name;
    @JsonProperty("organization_name")
    private String orgName;
    @JsonProperty("amount")
    private int amount;

    public Product(int id, String name, String orgName, int amount) {
        this.id = id;
        this.name = name;
        this.orgName = orgName;
        this.amount = amount;
    }

    @JsonCreator
    public Product(@JsonProperty("product_name") String name,@JsonProperty("organization_name") String orgName,@JsonProperty("amount") int amount) {
        this.name = name;
        this.orgName = orgName;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id == product.id && getAmount() == product.getAmount() && Objects.equals(getName(), product.getName()) && getOrgName().equals(product.getOrgName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getOrgName(), getAmount());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orgName='" + orgName + '\'' +
                ", amount=" + amount +
                '}';
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
