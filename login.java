import java.util.Scanner; // Importa ferramenta para ler dados do usuário

public class SistemaLogin {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Cria leitor do teclado

        // Usuário e senha "fixos" (simulando um banco de dados)
        String usuarioCorreto = "admin";
        String senhaCorreta = "1234";

        System.out.println("=== SISTEMA DE LOGIN ===");

        // Pedindo dados do usuário
        System.out.print("Digite o usuário: ");
        String usuario = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        // Verificação do login
        if (usuario.equals(usuarioCorreto) && senha.equals(senhaCorreta)) {
            System.out.println("✅ Login realizado com sucesso!");
        } else {
            System.out.println("❌ Usuário ou senha incorretos!");
        }

        scanner.close(); // Fecha o scanner
    }
}
