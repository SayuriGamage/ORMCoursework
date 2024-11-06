package lk.ijse.dao;

import lk.ijse.dao.impl.UserdaoImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;


    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }


    public enum DAOTypes{
        user
    }
    public SuperDAo getDao(DAOTypes daoTypes){
        switch (daoTypes){
            case user :
                return new UserdaoImpl();
        }
        return null;
    }
}
