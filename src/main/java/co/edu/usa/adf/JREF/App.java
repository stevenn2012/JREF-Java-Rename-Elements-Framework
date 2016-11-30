package co.edu.usa.adf.JREF;

import co.edu.usa.adf.JREF.admins.AdminBootstrap;
import co.edu.usa.adf.JREF.admins.AdminMaterialize;

public class App {
    public static void main( String[] args ){
        System.out.println(new AdminMaterialize("Materialize", "mat", "materialize.json").init());
        System.out.println(new AdminBootstrap("Bootstrap", "bs", "bootstrap.json").init());
    }
}
