package com.erobic.springit.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by robik on 12/12/16.
 */
@Entity
public class TransactionDemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionDemoEntityChild> transactionDemoEntityChildren;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TransactionDemoEntityChild> getTransactionDemoEntityChildren() {
        return transactionDemoEntityChildren;
    }

    public void setTransactionDemoEntityChildren(List<TransactionDemoEntityChild> transactionDemoEntityChildren) {
        this.transactionDemoEntityChildren = transactionDemoEntityChildren;
    }
}
