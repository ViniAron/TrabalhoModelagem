import dao.*;
import model.*;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
        OperarioDAO operarioDAO = new OperarioDAO();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        MaterialDAO materialDAO = new MaterialDAO();

        Projeto projeto = new Projeto();
        projeto.setNomeProjeto("Construção de Edifício");
        projeto.setLocal("São Paulo");
        projeto.setDataInicio(new Date());
        projeto.setDataTermino(new Date());

        projetoDAO.inserirProjeto(projeto);
        System.out.println("Projeto inserido com sucesso!");

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setNomeEngenheiro("Carlos Silva");
        engenheiro.setEspecialidade("Engenharia Civil");

        engenheiroDAO.inserirEngenheiro(engenheiro);
        System.out.println("Engenheiro inserido com sucesso!");

        Operario operario = new Operario();
        operario.setNomeOperario("João Santos");
        operario.setFuncao("Pedreiro");

        operarioDAO.inserirOperario(operario);
        System.out.println("Operário inserido com sucesso!");

        Equipamento equipamento = new Equipamento();
        equipamento.setNomeEquipamento("Betoneira");
        equipamento.setTipo("Pesado");

        equipamentoDAO.inserirEquipamento(equipamento);
        System.out.println("Equipamento inserido com sucesso!");

        Material material = new Material();
        material.setNomeMaterial("Cimento");
        material.setQuantidade(100);

        materialDAO.inserirMaterial(material);
        System.out.println("Material inserido com sucesso!");

        List<Projeto> projetos = projetoDAO.listarProjetos();
        for (Projeto p : projetos) {
            System.out.println("Projeto: " + p.getNomeProjeto() + ", Local: " + p.getLocal());
        }

        List<Engenheiro> engenheiros = engenheiroDAO.listarEngenheiros();
        for (Engenheiro e : engenheiros) {
            System.out.println("Engenheiro: " + e.getNomeEngenheiro() + ", Especialidade: " + e.getEspecialidade());
        }

        List<Operario> operarios = operarioDAO.listarOperarios();
        for (Operario o : operarios) {
            System.out.println("Operário: " + o.getNomeOperario() + ", Função: " + o.getFuncao());
        }

        List<Equipamento> equipamentos = equipamentoDAO.listarEquipamentos();
        for (Equipamento eq : equipamentos) {
            System.out.println("Equipamento: " + eq.getNomeEquipamento() + ", Tipo: " + eq.getTipo());
        }

        List<Material> materiais = materialDAO.listarMateriais();
        for (Material m : materiais) {
            System.out.println("Material: " + m.getNomeMaterial() + ", Quantidade: " + m.getQuantidade());
        }

        List<Engenheiro> engenheirosPorProjeto = engenheiroDAO.listarEngenheirosPorProjeto(projeto.getIdProjeto());
        for (Engenheiro eng : engenheirosPorProjeto) {
            System.out.println("Engenheiro no projeto: " + eng.getNomeEngenheiro());
        }

        List<Operario> operariosPorProjeto = operarioDAO.listarOperariosPorProjeto(projeto.getIdProjeto());
        for (Operario op : operariosPorProjeto) {
            System.out.println("Operário no projeto: " + op.getNomeOperario());
        }

        List<Equipamento> equipamentosPorProjeto = equipamentoDAO.listarEquipamentosPorProjeto(projeto.getIdProjeto());
        for (Equipamento eqp : equipamentosPorProjeto) {
            System.out.println("Equipamento no projeto: " + eqp.getNomeEquipamento());
        }

        List<Material> materiaisPorProjeto = materialDAO.listarMateriaisPorProjeto(projeto.getIdProjeto());
        for (Material mat : materiaisPorProjeto) {
            System.out.println("Material no projeto: " + mat.getNomeMaterial());
        }
    }
}
