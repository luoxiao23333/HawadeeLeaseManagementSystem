package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Duty {
    private Integer id;
    private String name;
    private Integer departmentId;

    public final static Duty GeneralManager = new Duty(1,"General Manager",1);
    public final static Duty LawyerDirector = new Duty(2,"Lawyer Director",1);
    public final static Duty BusinessStuff = new Duty(3,"Business Stuff",1);

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Duty)){
            return false;
        }
        return Objects.equals(this.id, ((Duty) obj).getId());
    }
}
