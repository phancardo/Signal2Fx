package com.projetCloud.projetCloudRESTWS.util;

import javax.validation.Payload;

public @interface ValidEmail {

    public String message() default "Not a valid email!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
