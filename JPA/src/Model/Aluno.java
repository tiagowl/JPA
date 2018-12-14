package Model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity //Indica que a classe será uma tabela no sql
public class Aluno {

	@Id //Indica que o atributo "matricula" será a chave primária da tabela
	private int matricula;
	private String nome;
	private String curso;
	
	@ManyToMany
	private Set<Disciplina> disciplinas;
	
	public Aluno() {
		
	}
	
	public Aluno(int matricula, String nome, String curso, Set<Disciplina> disciplinas) {
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
		this.disciplinas = disciplinas;
	}
	
	@Override
	public String toString() {
		
		return "Aluno [matricula=" +matricula+ ", nome=" +nome+ ", curso=" +curso+ "]" ;
	}
	
	public Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	
}
