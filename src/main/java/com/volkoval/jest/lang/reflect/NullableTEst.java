package com.volkoval.jest.lang.reflect;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 18.02.14
 * Time: 22:00
 */
public class NullableTest {

    private static int seed = 0;

    public static Object fillNullPointers(Object obj) throws NotFoundException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(obj) == null) {
                    try {
                        Object fieldObj = field.getType().newInstance();
                        // recursively fill all references to not null values
                        NullableTest.fillNullPointers(fieldObj);
                        // set field
                        field.set(obj, fieldObj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Object can't be fulled");
                    } catch (InstantiationException e) {
                        try {
                            field.set(obj, SilentObjectCreator.create(field.getType()));
                        } catch (Throwable th) {
                            // extend superclass
                            ClassPool pool = ClassPool.getDefault();
                            CtClass cc = pool.makeClass("Clazz" + seed);
                            seed++;
                            // set abstract class as superclass
                            try {
                                cc.setSuperclass(pool.get(field.getType().getName()));
                            } catch (CannotCompileException e2) {
                                e2.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
//                            throw new RuntimeException("Object can't be fulled");
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return obj;
    }

    public static void main(String[] args) {
        try {
            LoadDataStatusTO statusTO = new LoadDataStatusTO();
            fillNullPointers(statusTO);
            for (Field field : statusTO.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(statusTO) == null) {
                    throw new RuntimeException("Null reference isn't filled");
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
