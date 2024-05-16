package biblioteca.example.biblioteca.Enum;

public enum EUsuario {
    ALUNO("Aluno"),
    PROFESSOR("Professor"),
    FUNCIONARIO("Funcionario");

    private String role;

    EUsuario(String role){
        this.role = role;
    }

    public String GetEUsuario(){
        return role;
    }
}
