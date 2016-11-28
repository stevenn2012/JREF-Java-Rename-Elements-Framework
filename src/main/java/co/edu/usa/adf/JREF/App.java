package co.edu.usa.adf.JREF;

import co.edu.usa.adf.Logic.JRef;

public class App {
    public static void main( String[] args ){
    	System.out.println(new JRef("Materialize",
    			"ref/pruebas/frameworks/materialize/css/materialize.css",
    			"ref/pruebas/salida/materializeModeOffline.css", 
    			"materialize.json").init()
    	);
    	
    	System.out.println(new JRef("Materialize",
    			"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css",
    			"ref/pruebas/salida/materializeModeOnline.css", 
    			"materialize.json").init()
    	);
    	
    	System.out.println(new JRef("Materialize",
    			//"ref/pruebas/frameworks/materialize/js/materialize.min.js",
    			"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js",
    			"ref/pruebas/salida/materializeModeOnline.js", 
    			"materialize.json").init()
    	);
    	
    	System.out.println(new JRef("Materialize",
    			"ref/pruebas/frameworks/materialize/js/materialize.min.js",
    			"ref/pruebas/salida/materializeModeOffline.js", 
    			"materialize.json").init()
    	);
    }
}
