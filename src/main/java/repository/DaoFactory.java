package repository;

public class DaoFactory {

    private static DaoFactory instance;

    private DaoFactory(){

    }

    public static DaoFactory getInstance() {
        return instance ==null ? instance=new DaoFactory() : instance;
    }
}
