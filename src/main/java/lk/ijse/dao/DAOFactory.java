package lk.ijse.dao;

import lk.ijse.dao.impl.CoursedaoImpl;
import lk.ijse.dao.impl.RegistrationdaoImpl;
import lk.ijse.dao.impl.StudentdaoImpl;
import lk.ijse.dao.impl.UserdaoImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;


    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }


    public enum DAOTypes{
        user,student,course,regi
    }
    public SuperDAo getDao(DAOTypes daoTypes){
        switch (daoTypes){
            case user :
                return new UserdaoImpl();

            case student:
                return new StudentdaoImpl();

            case course:
            return new CoursedaoImpl();

            case regi:
                return new RegistrationdaoImpl();
        }

        return null;
    }
}
