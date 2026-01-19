CREATE OR ALTER PROCEDURE sp_validar_login
  @p_email VARCHAR(100),
  @p_senha VARCHAR(100)
AS
BEGIN
  SET NOCOUNT ON;

  SELECT idusuario, nome, email, status, eh_admin
  FROM usuarios
  WHERE email = @p_email
    AND senha = CONVERT(VARCHAR(64), HASHBYTES('SHA2_256', @p_senha), 2)
    AND status = 'A';
END
GO