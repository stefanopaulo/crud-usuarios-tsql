CREATE DATABASE crud_usuario
GO

USE crud_usuario
GO

DROP TABLE IF EXISTS DBO.usuarios
GO

CREATE TABLE usuarios
(
  idusuario INT IDENTITY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(50) NOT NULL,
  senha VARCHAR(64) NOT NULL,
  status CHAR(1) DEFAULT 'A',
  eh_admin BIT DEFAULT 0,
  data_criacao DATETIME DEFAULT GETDATE(),
  data_atualizacao DATETIME

    CONSTRAINT pk_usuario PRIMARY KEY (idusuario),
  CONSTRAINT uq_email_usuario UNIQUE (email),
  CONSTRAINT ck_status_usuario CHECK (status IN ('A', 'I'))
)
GO

CREATE OR ALTER VIEW vw_dados_usuarios
AS
  SELECT
    u.idusuario,
    u.nome,
    u.email,
    u.status,
    u.eh_admin,
    u.data_criacao,
    u.data_atualizacao
  FROM usuarios u
GO

CREATE OR ALTER PROC sp_lista_usuarios_ativos
AS
BEGIN
  SELECT
    vw.idusuario,
    vw.nome,
    vw.email,
    FORMAT(DATEADD(HOUR, -3, vw.data_criacao), 'dd/MM/yyyy HH:mm') AS data_criacao_ptbr,
    ISNULL(FORMAT(DATEADD(HOUR, -3, vw.data_atualizacao), 'dd/MM/yyyy HH:mm'), 'Sem atualização') AS data_atualizacao_ptbr
  FROM vw_dados_usuarios vw
  WHERE vw.status = 'A'
  ORDER BY vw.nome
END
GO

/* CRUD - BUSCAR POR ID */
CREATE OR ALTER PROC sp_buscar_usuario_por_id
  @p_idusuario INT
AS
BEGIN
  SET NOCOUNT ON;

  IF NOT EXISTS (SELECT 1
  FROM vw_dados_usuarios
  WHERE idusuario = @p_idusuario)
    BEGIN
    ;THROW 50006, 'Usuário não encontrado.', 1;
  END

  SELECT
    idusuario,
    nome,
    email,
    status,
    FORMAT(DATEADD(HOUR, -3, data_criacao), 'dd/MM/yyyy HH:mm') AS data_criacao_ptbr,
    ISNULL(FORMAT(DATEADD(HOUR, -3, data_atualizacao), 'dd/MM/yyyy HH:mm'), 'Sem atualização') AS data_atualizacao_ptbr
  FROM vw_dados_usuarios
  WHERE idusuario = @p_idusuario;
END
GO