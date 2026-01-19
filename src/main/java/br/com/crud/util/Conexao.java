package br.com.crud.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.crud.exceptions.ConexaoException;
import br.com.crud.exceptions.PropertiesException;

public class Conexao {

	public static Connection getConexao() {
		try {
			Properties prop = getProperties();
			String driver = prop.getProperty("driver");

			if (driver == null || driver.isEmpty()) {
				throw new PropertiesException("Propriedade 'driver' não encontrada");
			}

			Class.forName(driver);

			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("password"));
		} catch (ClassNotFoundException e) {
			throw new ConexaoException("Erro ao carregar driver do SQL Server", e);
		} catch (SQLException e) {
			throw new ConexaoException(
					"Erro ao conectar ao SQL Server no Docker. Verifique se a porta 1433 está aberta.", e);
		}
	}

	private static Properties getProperties() {
		Properties prop = new Properties();
		try (InputStream input = Conexao.class.getResourceAsStream("/db.properties")) {
			if (input == null) {
				throw new PropertiesException("Arquivo db.properties não encontrado!");
			}
			prop.load(input);
		} catch (IOException e) {
			throw new PropertiesException("Erro ao ler o arquivo de configuração");
		}
		return prop;
	}

}
