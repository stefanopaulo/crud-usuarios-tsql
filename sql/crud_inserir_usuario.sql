CREATE OR ALTER PROC sp_inserir_usuario
  @p_nome VARCHAR(100),
  @p_email VARCHAR(50),
  @p_senha VARCHAR(64)
AS
BEGIN
  SET NOCOUNT ON;

  IF @p_nome IS NULL OR @p_email IS NULL OR @p_senha IS NULL
    THROW 50000, 'Todos os campos são obrigatórios.', 1;

  IF EXISTS (SELECT 1
  FROM usuarios
  WHERE email = @p_email)
    THROW 50001, 'Este e-mail já está cadastrado.', 1;

  BEGIN TRY
    BEGIN TRANSACTION
      INSERT INTO usuarios
    (nome, email, senha)
  VALUES
    (@p_nome, @p_email, CONVERT(VARCHAR(64), HASHBYTES('SHA2_256', @p_senha), 2));

    DECLARE @v_novo_id INT = SCOPE_IDENTITY();

    COMMIT TRANSACTION
    
    SELECT 'Sucesso' AS Status, 'Usuário ' + @p_nome + ' inserido com sucesso!' AS Mensagem, @v_novo_id AS novoId;

  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0
      ROLLBACK TRANSACTION;

    DECLARE @err NVARCHAR(4000) = ERROR_MESSAGE();
    RAISERROR(@err, 16, 1);
  END CATCH
END
GO