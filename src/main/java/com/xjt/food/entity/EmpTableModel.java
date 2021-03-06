package com.xjt.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class EmpTableModel {
    int code;
    String message;
    int count;
    List<Employee> data;

    public EmpTableModel() {
        super();
    }

    public EmpTableModel(int code, String message, int count,
                         List<Employee> data) {
        super();
        this.code = code;
        this.message = message;
        this.count = count;
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + code;
        result = prime * result + count;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result
                + ((message == null) ? 0 : message.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EmpTableModel other = (EmpTableModel) obj;
        if (code != other.code) {
            return false;
        }
        if (count != other.count) {
            return false;
        }
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (message == null) {
            return other.message == null;
        } else {
            return message.equals(other.message);
        }
    }
}
