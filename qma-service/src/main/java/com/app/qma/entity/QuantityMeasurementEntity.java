package com.app.qma.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.io.Serializable;

@Entity
@Table(name="quantity_measurements")
public class QuantityMeasurementEntity implements Serializable  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String operand1;
    private String operand2;
    private String operation;
    private String result;

    private String userEmail; // ✅ ADD THIS

    private LocalDateTime createdAt;
    private static final long serialVersionUID = 1L;

    public QuantityMeasurementEntity(){}

    // ✅ ADD userEmail to constructor
    public QuantityMeasurementEntity(String operand1, String operand2,
                                      String operation, String result,
                                      String userEmail){
        this.operand1  = operand1;
        this.operand2  = operand2;
        this.operation = operation;
        this.result    = result;
        this.userEmail = userEmail; // ✅
    }

    @PrePersist
    public void onCreate(){ this.createdAt = LocalDateTime.now(); }

    public Long getId()              { return id; }
    public String getOperand1()      { return operand1; }
    public void setOperand1(String o){ this.operand1 = o; }
    public String getOperand2()      { return operand2; }
    public void setOperand2(String o){ this.operand2 = o; }
    public String getOperation()     { return operation; }
    public void setOperation(String o){ this.operation = o; }
    public String getResult()        { return result; }
    public void setResult(String r)  { this.result = r; }
    public String getUserEmail()     { return userEmail; }       // ✅
    public void setUserEmail(String e){ this.userEmail = e; }    // ✅
    public LocalDateTime getCreatedAt(){ return createdAt; }
}