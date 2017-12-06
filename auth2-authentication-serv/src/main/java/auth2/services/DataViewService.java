package auth2.services;

import org.hsqldb.util.DatabaseManagerSwing;

public class DataViewService {
    
    public static void runDataViewer () {
        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password",""
                /*, "--script", "db/sql/select-users.sql" */});
    }
}

    
