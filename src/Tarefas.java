package sgrh;
import java.io.*;
import java.util.*;
public class Tarefas {
    //Objectosfalse
    private Vector vt = new Vector(); Funcionario fun;
    private Validacoes v;
    private Adpt_Ficheiro ficheiro;
    private Menu m;
    
    //Contructor
    public Tarefas(){
        ficheiro = new  Adpt_Ficheiro();
        m= new Menu();
    }
    public void cadastro() {
    String nome, apelido, bi, genero, endereco, nacionalidade, estadoCivil, dataNasc,dataRegistro, areaForm, areaLeciona,instituicao,cargo;
    long telefone;
    int codFunc, nuit,nivelEscolar,anoDeFormacao;
    byte anosExperiencia;
    float salario=0;
    char criterio;
        
        v=new Validacoes();
        nome=v.validarNome("Nome(no minimo 3 letras e no maximo 20)",3,20);
        apelido=v.validarNome("Apelido (Deve conter no minimo 3 letras e no maximo 20 e sem espaco)",3,20);
        bi=v.validarBI("BI Deve conter 12 numeros e uma letra",(byte)13);
        codFunc=v.validarInt("Codigo do funcionario: (Com 5 digitos)", 10000, 99999);
        genero=v.adapatarOpcoes(" Sexo: \n 1-Femenio \n 2-Masculino ", "F" , "M");
        endereco = v.NotValid("Introduz o seu actual endereço: ");
        nacionalidade=v.validarSemNr("Nacionalidade", (byte) 5, (byte)20);
        estadoCivil=v.validarEstCivil();
        dataNasc =  v.validarData(" de Nascimento \n NB:Deve ter idade igual o superior a 18 anos. ", 1950, 2001);
        telefone = v.validarLong(" Número de telefone ", 820000000, 870000000);
        nuit = v.validarInt(" NUIT ",100000000 , 999999999);
        dataRegistro = v.dataActual();
         m.menuSal();
        criterio = v.adapatarOpcoes(" Funçao: \n 1-Administração \n 2-Explicação ", "A", "E").charAt(0);
        if(criterio == 'A' || criterio == 'a'){
            cargo = v.adaptarCargo();
            criaAdmin (nome, apelido, bi, codFunc, genero, endereco, nacionalidade, estadoCivil, dataNasc,telefone,  nuit,dataRegistro,salario, cargo);
        }
        else{
            if(criterio == 'E' || criterio == 'e' ){
                areaForm = v.adaptarArea();
                areaLeciona = v.validarSemNr(" Area que Lecciona ",5, 15);
                criterio = v.adapatarOpcoes(" Nível de Formação: \n 1-Estudante \n 2-Licenciado ", "E", "L").charAt(0);
                if(criterio=='L' || criterio == 'l'){
                    anosExperiencia = v.validarByte(" Anos de experiência ", 0, 20);
                    anoDeFormacao = v.validarInt("Ano de Formação ", 1990, 2019);
                    criaLicenciado(nome, apelido, bi, codFunc, genero, endereco, nacionalidade, estadoCivil, dataNasc,telefone, nuit, dataRegistro, salario,areaForm, areaLeciona, anosExperiencia, anoDeFormacao);
                }
                else{
                    if(criterio == 'E' || criterio == 'e'){
                        nivelEscolar = v.validarInt("Nível escolar", 1, 5);
                        instituicao = v.validarSemNr("Instituição que frequenta", 2, 20);
                        criaEstudante(nome, apelido,bi, codFunc, genero, endereco, nacionalidade, estadoCivil, dataNasc,telefone,nuit, dataRegistro,salario, areaForm, areaLeciona, nivelEscolar, instituicao);
                    }
                }
            }
        }
        System.out.println("==============================================================================");
        System.out.println("======================= Cadastrado com Sucesso ===============================");
        System.out.println("==============================================================================");
    }
    // Criacao de Objectos
    public void criaAdmin (String nome, String apelido, String bi,int codFunc, String genero, String endereco, String nacionalidade, String estadoCivil, String dataNasc,long telefone, int nuit,String dataRegistro, float salario,String cargo) {
        Admin a= new Admin ();
        salario = a.SalarioLiquido(0, 0);
        a.setNome(nome);
        a.setApelido(apelido);
        a.setBi(bi);
        a.setCodFunc(codFunc);
        a.setGenero(genero);
        a.setEndereco(endereco);
        a.setNacionalidade(nacionalidade);
        a.setEstadoCivil(estadoCivil);
        a.setDataNasc(dataNasc);
        a.setTelefone(telefone);
        a.setNuit(nuit);
        a.setDataRegistro(dataRegistro);
        a.setSalario(salario);
        a.setCargo(cargo);
        vt.addElement(a);
    }
    public void criaLicenciado(String nome, String apelido, String bi,int codFunc, String genero, String endereco, String nacionalidade, String estadoCivil, String dataNasc,long telefone, int nuit,String dataRegistro,float salario,String areaForm,String areaLeciona,byte anosExperiencia, int anoDeFormacao) {
        Licenciado l = new Licenciado ();
         salario = l.salarioLiquido(0, 0);
        l.setNome(nome);
        l.setApelido(apelido);
        l.setBi(bi);
        l.setCodFunc(codFunc);
        l.setGenero(genero);
        l.setEndereco(endereco);
        l.setNacionalidade(nacionalidade);
        l.setEstadoCivil(estadoCivil);
        l.setDataNasc(dataNasc);
        l.setTelefone(telefone);
        l.setNuit(nuit);
        l.setDataRegistro(dataRegistro);
        l.setSalario(salario);
        l.SetAreaForm(areaForm);
        l.setAreaLeciona(areaLeciona);
        l.setAnosExperiencia(anosExperiencia);
        l.setAnoDeFormacao(anoDeFormacao);
        vt.addElement(l);
        System.out.println(l.toString());
    }
    public void criaEstudante(String nome, String apelido, String bi,int codFunc, String genero, String endereco, String nacionalidade, String estadoCivil, String dataNasc,long telefone, int nuit,String dataRegistro,float salario,String areaForm,String areaLeciona,int nivelEscolar, String instituicao) {
        Estudante e = new Estudante();
        salario = e.salarioLiquido(0, 0);
        e.setNome(nome);
        e.setApelido(apelido);
        e.setBi(bi);
        e.setCodFunc(codFunc);
        e.setGenero(genero);
        e.setEndereco(endereco);
        e.setNacionalidade(nacionalidade);
        e.setEstadoCivil(estadoCivil);
        e.setDataNasc(dataNasc);
        e.setTelefone(telefone);
        e.setNuit(nuit);
        e.setDataRegistro(dataRegistro);
        e.setSalario(salario);
        e.SetAreaForm(areaForm);
        e.setAreaLeciona(areaLeciona);
        e.setNivel(nivelEscolar);
        e.setInstituicao(instituicao);
        vt.addElement(e);
        System.out.println(e.toString());
    }
    //Escrita No Ficheiro
    public void escreverFicheiroTXT(String nomeFich){ficheiro.esc_VectorEmTXT(nomeFich, vt, false); };
    public void escreverFicheiroDAT(String nomeFich){
        ficheiro.esc_VectorEmDAT(nomeFich, vt);
    };
   
     //toString
    public String toString(){
        String ver="";
        Funcionario f;
        for(int i = 0; i < vt.size(); i++){
            f = (Funcionario)vt.elementAt(i);
            ver+= ver.concat(f.toString() + "\n");
        }
        return ver;
    }
    
}