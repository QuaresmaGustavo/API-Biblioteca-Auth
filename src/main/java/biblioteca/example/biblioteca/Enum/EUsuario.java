package biblioteca.example.biblioteca.Enum;

public enum EUsuario {
    USUARIO("Usuario"),
    FUNCIONARIO("Funcionario");

    private String role;

    EUsuario(String role){
        this.role = role;
    }

    public String GetEUsuario(){
        return role;
    }
}
