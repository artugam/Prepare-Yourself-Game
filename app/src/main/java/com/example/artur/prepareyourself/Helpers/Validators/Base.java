package com.example.artur.prepareyourself.Helpers.Validators;

public class Base {


    public static boolean isEmptyString(String string)
    {
        if(string.length() > 0)
        {
            return false;
        }

        return true;
    }



}
