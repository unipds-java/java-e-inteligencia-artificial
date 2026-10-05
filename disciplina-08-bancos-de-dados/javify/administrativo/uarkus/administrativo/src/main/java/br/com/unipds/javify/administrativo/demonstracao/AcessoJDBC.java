package br.com.unipds.javify.administrativo.demonstracao;

import br.com.unipds.javify.administrativo.domain.Endereco;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.*;

@ApplicationScoped
public class AcessoJDBC {
    @Inject @ConfigProperty(name = "quarkus.datasource.jdbc.url") String url;
    @Inject @ConfigProperty(name = "quarkus.datasource.username") String user;
    @Inject @ConfigProperty(name = "quarkus.datasource.password") String pass;


    public void executar() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);


            ps = conn.prepareStatement("SELECT * FROM endereco WHERE codigo_postal = ?");
            ps.setString(1, "01508000");
            rs = ps.executeQuery();


            Endereco e = null;
            if (rs.next()) {
                e = new Endereco();
                e.setCodigoPostal(rs.getString("codigo_postal"));
                e.setLogradouro(rs.getString("logradouro"));
                e.setBairro(rs.getString("bairro"));
                System.out.printf("Endereço com JDBC: %s \n ", e);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
