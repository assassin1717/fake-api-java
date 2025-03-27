package com.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Boolean isValid() {
        return id != 0 && name != null && email != null && phone != null;
    }

    public Boolean isPartialValid() {
        return name != null && email != null && phone != null;
    }
}
