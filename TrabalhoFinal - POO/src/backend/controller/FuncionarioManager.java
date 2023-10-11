package backend.controller;

import com.google.gson.Gson;

import backend.model.Funcionario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class FuncionarioManager implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Funcionario> funcionarios;

    public FuncionarioManager() {
        funcionarios = new ArrayList<>();
    }

    public void adicionarFuncionario(String nome, String cargo, String horarioTrabalho) {
        Funcionario funcionario = new Funcionario(nome, cargo, horarioTrabalho);
        funcionarios.add(funcionario);
    }

    public void adicionarFuncionarioFromJSON(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Gson gson = new Gson();
            
            
            Funcionario[] funcionariosArray = gson.fromJson(reader, Funcionario[].class);

            for (Funcionario funcionario : funcionariosArray) {
                String nome = funcionario.getNome();
                String cargo = funcionario.getCargo();
                String horarioTrabalho = funcionario.getHorarioTrabalho();

                adicionarFuncionario(nome, cargo, horarioTrabalho);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exibirFuncionarios() {
        for (Funcionario funcionario : funcionarios) {
            funcionario.exibirInformacoes();
            System.out.println();
        }
    }
    
    public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}
   
}
