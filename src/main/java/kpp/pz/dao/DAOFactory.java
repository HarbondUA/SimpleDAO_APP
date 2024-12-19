package kpp.pz.dao;

import java.sql.SQLException;

public class DAOFactory {

    public static IMyDAO getDAOInstance(TypeDAO type) throws SQLException {
        IMyDAO dao = null;
        if (type == TypeDAO.MySQL) {
            dao = new MySQLDAO();
        }
        if (type == TypeDAO.MyList) {

            dao = new CollectionLDAO();
        }
        return dao;
    }
}


