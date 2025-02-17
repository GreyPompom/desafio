CREATE TABLE clientes (
id SERIAL PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
limite INT NOT NULL,
saldo INT NOT NULL DEFAULT 0
);

CREATE TABLE transacoes (
id SERIAL PRIMARY KEY,
valor INT NOT NULL,
tipo CHAR(1) NOT NULL,
descricao VARCHAR(10) NOT NULL,
realizada_em TIMESTAMP NOT NULL DEFAULT NOW(),
cliente_id INT REFERENCES clientes(id)
);

DO $$
BEGIN
INSERT INTO clientes (nome, limite, saldo)
VALUES
    ('o barato sai caro', 100000, 0),
    ('zan corp ltda', 80000, 0),
    ('les cruders', 1000000, 0),
    ('padaria joia de cocaia', 10000000, 0),
    ('kid mais', 500000, 0);
END; $$;