package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Duty {
    private Integer id;
    private String name;
    private Integer departmentId;

    public final static Duty GeneralManager = new Duty(1,"General Manager",1);
    public final static Duty LawyerDirector = new Duty(2,"Lawyer Director",1);
    public final static Duty BusinessStuff = new Duty(3,"Business Stuff",1);
}
