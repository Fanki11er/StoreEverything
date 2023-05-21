package com.example.storeeverything.Dtos;

import com.example.storeeverything.Entities.User;

public class UserDto {

    private User user;

    public UserDto(User user){
        this.user = user;
    }

    public long getId(){
        return user.getId();
    }

    public String getLogin(){
        return user.getLogin();
    }

    public String getFirstName(){
        return  user.getFirstName();
    }

    public  String getSurname(){
        return  user.getSurname();
    }

    public String getRole(){
        return user.getRole().name();
    }

    public int getAge(){
        return user.getAge();
    }

}
