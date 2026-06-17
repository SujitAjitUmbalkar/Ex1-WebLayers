package com.weblayerexample.weblayers.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {
    String message() default "Role of Employee can either be USER or ADMIN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

//added  own valid  Annotations ( Use @interface to create a custom annotation,
// define its applicable location using @Target,
// set its availability using @Retention (SOURCE, CLASS, or RUNTIME),
// declare annotation elements with return types and optional default values,
// apply the annotation to the required program element,
// and access it using the Reflection API if the retention policy is RUNTIME.