package com.ds.spring.boot.hazelcast;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString

public class Employee  implements Serializable {
    private String empId;
    private String empName;
}
