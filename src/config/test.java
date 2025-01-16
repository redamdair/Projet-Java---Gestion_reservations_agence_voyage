package config;

import java.sql.Connection;

//---------- Test de connexion à la base de données "projet_RM_VN_TD" ----------//
public class test {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Connexion réussie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
