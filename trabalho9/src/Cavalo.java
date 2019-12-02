import java.io.Serializable;

public class Cavalo extends Mamifero
{

	public Cavalo(String nome, int idade, String dono) {
		super(nome, idade, dono);
		this.especie = "Cavalo";
	}

	@Override
	public String soar() {
		return "hiin in in hinir";
	}
	
	


	
}
