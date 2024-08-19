import dao.AutorDAO;
import dao.LivroDAO;
import model.Autor;
import model.Livro;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutorDAO autorDAO = new AutorDAO();
        LivroDAO livroDAO = new LivroDAO();

        // Inserindo autores
        Autor autor1 = new Autor(0, "J.K. Rowling", "Britânica");
        Autor autor2 = new Autor(0, "George R.R. Martin", "Americano");
        autorDAO.inserirAutor(autor1);
        autorDAO.inserirAutor(autor2);

        // Inserindo livros
        Livro livro1 = new Livro(0, "Harry Potter e a Pedra Filosofal", 1997, 1); // ID_Autor = 1
        Livro livro2 = new Livro(0, "Harry Potter e a Câmara Secreta", 1998, 1); // ID_Autor = 1
        Livro livro3 = new Livro(0, "A Guerra dos Tronos", 1996, 2); // ID_Autor = 2
        livroDAO.inserirLivro(livro1);
        livroDAO.inserirLivro(livro2);
        livroDAO.inserirLivro(livro3);

        // Listando todos os autores
        System.out.println("Autores:");
        List<Autor> autores = autorDAO.listarAutores();
        for (Autor a : autores) {
            System.out.println("ID: " + a.getIdAutor() + ", Nome: " + a.getNome() + ", Nacionalidade: " + a.getNacionalidade());
        }

        // Listando todos os livros
        System.out.println("\nLivros:");
        List<Livro> livros = livroDAO.listarLivros();
        for (Livro l : livros) {
            System.out.println("ID: " + l.getIdLivro() + ", Título: " + l.getTitulo() + ", Ano: " + l.getAnoPublicacao() + ", Autor ID: " + l.getIdAutor());
        }

        // Listando todos os livros de um autor específico
        System.out.println("\nLivros de J.K. Rowling:");
        List<Livro> livrosAutor1 = livroDAO.listarLivrosPorAutor(1); // ID do autor J.K. Rowling
        for (Livro l : livrosAutor1) {
            System.out.println("ID: " + l.getIdLivro() + ", Título: " + l.getTitulo() + ", Ano: " + l.getAnoPublicacao());
        }

        // Atualizando um autor
        autor1.setNome("Joanne Rowling");
        autorDAO.atualizarAutor(autor1);
        System.out.println("\nAutor atualizado:");
        Autor autorAtualizado = autorDAO.listarAutores().stream().filter(a -> a.getIdAutor() == 1).findFirst().orElse(null);
        if (autorAtualizado != null) {
            System.out.println("ID: " + autorAtualizado.getIdAutor() + ", Nome: " + autorAtualizado.getNome() + ", Nacionalidade: " + autorAtualizado.getNacionalidade());
        }

        // Excluindo um livro
        livroDAO.excluirLivro(livro3.getIdLivro());
        System.out.println("\nLivros após exclusão:");
        List<Livro> livrosAtualizados = livroDAO.listarLivros();
        for (Livro l : livrosAtualizados) {
            System.out.println("ID: " + l.getIdLivro() + ", Título: " + l.getTitulo() + ", Ano: " + l.getAnoPublicacao());
        }

        // Excluindo um autor
        autorDAO.excluirAutor(2); // ID do autor George R.R. Martin
        System.out.println("\nAutores após exclusão:");
        List<Autor> autoresAtualizados = autorDAO.listarAutores();
        for (Autor a : autoresAtualizados) {
            System.out.println("ID: " + a.getIdAutor() + ", Nome: " + a.getNome() + ", Nacionalidade: " + a.getNacionalidade());
        }
    }
}
