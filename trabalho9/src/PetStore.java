import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PetStore 
{
	ArrayList<Mamifero> mamiferos;
	
	public PetStore() {
	 mamiferos = new ArrayList<Mamifero>();
	}
	
	public void adicionarAnimal(Mamifero mani)
	{
		mamiferos.add(mani);
	}
	
	public void excluirAnimal(Mamifero mani)
	{
		if(this.mamiferos.contains(mani))
		{
			this.mamiferos.remove(mani);
			System.out.println("O animal " + mani.toString() + "Foi removido da lista \n");
		}
		else
		{
			System.out.println("Animal Inexistente");
		}
		
	}
	
	public void excluirAnimais()
	{
		mamiferos.clear();
		System.out.println("Animais excluidos com sucesso");
	}
	
	public void gravarAnimais()
	{
		ObjectOutputStream outputStream = null;
		try {
		outputStream = new ObjectOutputStream(new FileOutputStream("Animais.dat"));
		for(Mamifero mani : mamiferos)
		{
			outputStream.writeObject(mani);
		}
		}catch(FileNotFoundException e) {System.out.println("Erro :" + e.getMessage());}catch(IOException e) {System.out.println("Erro :" + e.getMessage());}
		finally 
		{
			try {
				if(outputStream!=null)
				{
					outputStream.close();
					System.out.println("Todos os animais foram salvos");
					
				}
				}catch(IOException e) 
				{
					System.out.println("Erro 3 :" + e.getMessage());
				}
		}
	}
	
	public void recuperarAnimais()
	{
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("Animais.dat"));
			Object obj = null;
			while((obj = inputStream.readObject()) != null)
			{
				if(obj instanceof Gato)
				{
					this.mamiferos.add((Gato)obj);
				}
				else if(obj instanceof Cao)
				{
					this.mamiferos.add((Cao)obj);
				}
				else if(obj instanceof Cavalo)
				{
					this.mamiferos.add((Cavalo)obj);
				}
			}
		}catch(EOFException e) {
			System.out.println("End of file reached");
		} 
		catch(FileNotFoundException e) {System.out.println("Erro 2:" + e.getMessage());}catch(ClassNotFoundException e) {System.out.println("Erro 5:" + e.getMessage());}
		catch(IOException e) {System.out.println("Erro 3:" + e.getMessage());}
		finally { 
			try{
			if(inputStream!=null)
			{
				inputStream.close();
				System.out.println("Animais recuperados com sucesso \n");
			}
		}catch(IOException e) {System.out.println("Erro 4:" + e.getMessage());}}
		
	}
	
	public void listarAnimais()
	{
		for(Mamifero m : mamiferos)
		{
		System.out.println(m.toString());
		}
		System.out.println("Total: " + mamiferos.size() +" animais listados com sucesso");
	}



	public static void main(String[] args)
	{
		
		PetStore pet = new PetStore();
		Gato felix = new Gato("Felix", 3, "Maria");
		Gato garfield = new Gato("Garfield",7,"Maria");
		Cao rex = new Cao("Rex", 2, "Jose");
		Cao toto = new Cao("Toto",5,"Jose");
		Cavalo spirit = new Cavalo("Spirit",8,"Jorge");
		pet.adicionarAnimal(spirit);
		pet.adicionarAnimal(felix);
		pet.adicionarAnimal(garfield);
		pet.adicionarAnimal(rex);
		pet.adicionarAnimal(toto);
		pet.listarAnimais();
		pet.gravarAnimais();
		
		pet.excluirAnimal(garfield);
		pet.listarAnimais();
		pet.excluirAnimais();
		pet.listarAnimais();
		pet.recuperarAnimais();
		pet.listarAnimais();
	}
}
