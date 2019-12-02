
public class Cao extends Mamifero 
{

	public Cao(String nome, int idade, String dono)
	{
		super(nome, idade, dono);
		this.especie ="Cao";
	}

	@Override
	public String soar() 
	{
		return "Au au";
	}
	
	

}
