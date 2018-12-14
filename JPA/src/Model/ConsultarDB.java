package Model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ConsultarDB {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("disciplinas");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Lista de Professores");
		Query q = em.createQuery("SELECT p FROM Professor p ORDER BY p.nome");
		List<Professor> list = q.getResultList();
		for(Professor professor : list) {
			System.out.println(professor);
		}
		
		System.out.println("Total: " + list.size());
		System.out.println("-------------------\n");
		
		System.out.println("Lista de alunos e disciplinas: ");
		
		q = em.createQuery("SELECT a FROM Aluno a");
		List<Aluno> alunos = q.getResultList();
		System.out.println("RA\t\Aluno\t\tDisciplinas");
		for (Aluno aluno : alunos) {
			   System.out.print(aluno.getMatricula());
			   System.out.print("\t" + aluno.getNome() + "\t\t");
			   for (Disciplina materia : aluno.getDisciplinas()) {
			    System.out.print(materia.getSigla() + " ");
			   }
			   System.out.println();
			  }
			  System.out.println("Total de alunos: " + alunos.size());
			  System.out.println("-------------------------------------\n");
			 
			  System.out.println("Lista de Alunos do Prof. Cesar Lattes:");
			  q = em.createQuery("SELECT DISTINCT a "
			  + " FROM Aluno a JOIN a.disciplinas d "
			  + " WHERE d.professor.nome = :prof");
			  q.setParameter("prof", "Cesar Lattes");
			  List<Aluno> lista1 = q.getResultList();
			  for (Aluno aluno : lista1) {
			   System.out.println(aluno);
			  }
			  System.out.println("Total de alunos: " + lista1.size());
			  System.out.println("-------------------------------------\n"); 
			 
			  System.out.println("Lista de Professores que dão aula quarta-feira pela manhã:");
			  q = em.createQuery("SELECT d FROM Disciplina d "
			  + " WHERE d.diaDaSemana = ?1 AND d.horaInicio >= ?2 AND d.horaFim <= ?3");
			  q.setParameter(1, Disciplina.DiaDaSemana.QUARTA);
			  q.setParameter(2, new Integer(8));
			  q.setParameter(3, new Integer(12));
			 
			  List<Disciplina> lista2 = q.getResultList(); 
			  System.out.println("Professor\t\tDia\tHorario");
			  for (Disciplina d : lista2) {
			   String linha = String.format("%s\t\t%s\t%02dh-%02dh", 
			   d.getProfessor().getNome(), 
			   d.getDiaDaSemana(), 
			   d.getHoraInicio(), 
			   d.getHoraFim());
			   System.out.println(linha);
			  }
			 
			  em.close();
			  emf.close();
			  }

	}

}
