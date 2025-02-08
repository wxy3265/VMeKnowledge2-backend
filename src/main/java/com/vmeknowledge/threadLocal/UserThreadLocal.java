package com.vmeknowledge.threadLocal;

public class UserThreadLocal {
    public static ThreadLocal<Integer> userThreadLocal = new ThreadLocal<>();

    public static void setCurrentId(Integer id) {
        userThreadLocal.set(id);
    }

    public static Integer getCurrentId() {
        return userThreadLocal.get();
    }

    public static void removeCurrentId() {
        userThreadLocal.remove();
    }

}
