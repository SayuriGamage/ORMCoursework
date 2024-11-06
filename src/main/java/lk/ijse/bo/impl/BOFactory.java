package lk.ijse.bo.impl;

import lk.ijse.bo.SuperBO;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory():boFactory;
    }


    public enum BOTypes{
        user,
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes) {
            case user:
                return new UserboImpl();
        }
        return null;
        }
    }

