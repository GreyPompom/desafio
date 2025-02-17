import { Alert } from "@mui/material";

type Props = {
  mensagem?: string;
  erro?: string;
};

export default function TransacaoMensagem({ mensagem, erro }: Props) {
  return (
    <>
      {erro && <Alert severity="error" sx={{ mt: 2 }}>{erro}</Alert>}
      {mensagem && <Alert severity="success" sx={{ mt: 2 }}>{mensagem}</Alert>}
    </>
  );
}
