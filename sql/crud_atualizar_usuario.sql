/* TRIGGER DE ATUALIZAÇÃO */
CREATE OR ALTER TRIGGER trg_atualizar_usuario
ON dbo.usuarios
AFTER UPDATE
AS
BEGIN
  SET NOCOUNT ON;

  IF NOT UPDATE(data_atualizacao)
  BEGIN
    UPDATE u
    SET u.data_atualizacao = GETDATE()
    FROM usuarios u
      INNER JOIN inserted i ON u.idusuario = i.idusuario;
  END
END
GO

/* CRUD - UPDATE */
CREATE OR ALTER PROC sp_atualizar_usuario
  @p_idusuario INT,
  @p_nome VARCHAR(100),
  @p_email VARCHAR(50),
  @p_senha VARCHAR(64)
AS
BEGIN
  SET NOCOUNT ON;

  BEGIN TRY
    BEGIN TRANSACTION;

      IF EXISTS (SELECT 1
  FROM usuarios
  WHERE email = @p_email AND idusuario <> @p_idusuario)
        THROW 50001, 'Este e-mail já está sendo usado por outro usuário.', 1;

      UPDATE usuarios 
      SET nome = @p_nome, 
          email = @p_email,
          senha = CONVERT(VARCHAR(64), HASHBYTES('SHA2_256', @p_senha), 2)
      WHERE idusuario = @p_idusuario;

      IF @@ROWCOUNT = 0
        THROW 50002, 'Usuário não encontrado para atualização.', 1;

    COMMIT TRANSACTION;

  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION;
    
    DECLARE @err NVARCHAR(4000) = ERROR_MESSAGE();
    RAISERROR(@err, 16, 1);
  END CATCH
END
GO