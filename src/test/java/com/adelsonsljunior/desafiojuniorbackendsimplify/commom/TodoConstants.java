package com.adelsonsljunior.desafiojuniorbackendsimplify.commom;

import com.adelsonsljunior.desafiojuniorbackendsimplify.model.Todo;

public class TodoConstants {

    public static final Todo TODO = new Todo("name", "description", 1);
    public static final Todo INVALID_TODO     = new Todo(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            2);
}
