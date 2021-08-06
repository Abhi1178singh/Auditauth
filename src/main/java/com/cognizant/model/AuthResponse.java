package com.cognizant.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {



    private String uid;

    private boolean isValid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
/*
    public AuthResponse(String uid, boolean isValid) {
        this.uid = uid;
        this.isValid = isValid;
    }

 */
}
