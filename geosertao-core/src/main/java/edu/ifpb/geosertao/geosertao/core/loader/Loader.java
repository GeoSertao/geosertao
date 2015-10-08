package edu.ifpb.geosertao.geosertao.core.loader;

import org.flywaydb.core.Flyway;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 * 
 * Esta classe executa o framework flyway {@link http://flywaydb.org/} o qual
 * serve para atualizar automaticamente o banco de dados executando os scripts
 * presentes no diretório src/main/resources/db/migration. Sendo assim, qualquer
 * mudança no esquema do banco ou qualquer script de povoamento de tabelas deverá
 * estar nesse diretório, tendo sua sequencia de execução comandada pelos prefixos
 * V1, V2, V3 ... Vn
 */
public class Loader {

    
    public static void main(String[] args) {
        Flyway flyway = new Flyway();        
        flyway.setDataSource(
                "jdbc:postgresql://localhost:5432/geosertao"
                , "postgres"
                , "123456"
        );
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();
    }
}
