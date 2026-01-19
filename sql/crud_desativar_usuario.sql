/* CRUD - SOFT DELETE */
CREATE OR ALTER PROC sp_desativar_usuario
  @p_idusuario INT
AS
BEGIN
  SET NOCOUNT ON;
  DECLARE @v_nome VARCHAR(100), @v_status CHAR(1);

  SELECT @v_nome = nome, @v_status = status
  FROM usuarios
  WHERE idusuario = @p_idusuario;

  IF @v_nome IS NULL
    THROW 50002, 'Usuário não encontrado na base de dados.', 1;

  IF @v_status = 'I'
    THROW 50003, 'Este usuário já se encontra desativado.', 1;

  BEGIN TRY
    BEGIN TRANSACTION
      UPDATE usuarios SET status = 'I' WHERE idusuario = @p_idusuario;
    COMMIT TRANSACTION

  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION;
    DECLARE @err NVARCHAR(4000) = ERROR_MESSAGE();
    RAISERROR(@err, 16, 1);
  END CATCH
END
GO