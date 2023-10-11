package backend;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persiste {
	public static void salva(final Object obj, final String file) {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(obj);
			out.flush();

			out.close();
			fileOut.close();
			System.out.println("Objeto salvo com sucesso em '" + file + "'");
			
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar em '" + file + "'");
			e.printStackTrace();
		}
	}
	
	public static Object carrega(final String file) {
		File arquivo = new File(file);

		if (!arquivo.exists()) {
			return null;
		}
		Object obj = null;

		try {
			FileInputStream fileIn = new FileInputStream(arquivo);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			obj = (Restaurante) in.readObject();

			in.close();
			fileIn.close();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Ocorreu um erro ao carregar o arquivo '" + file + "'");
			e.printStackTrace();
		}
		
		return obj;
	}
}
