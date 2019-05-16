package sgrh;
import java.io.*;
import java.util.*;
public class Tarefas {
    private Funcionario fun;
    static Vector vt = new Vector();
    private int nr,cont;
    private Validacoes v;
    String nome, apelido, bi, genero, endereco, nacionalidade, estadoCivil, dataNasc,dataRegistro, areaForm, areaLeciona,instituicao,cargo;
    long telefone;
    int codFunc, nuit,nivelEscolar,anoDeFormacao;
    byte anosExperiencia;
    float salario=0;
    char criterio;
    Salario s = new Salario();
    private BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
    public Tarefas(){}
    public void lerFich (String nomeFich){
        StringTokenizer str;
        String linha = "";
        try{
            BufferedReader br = new BufferedReader (new FileReader(nomeFich));
            linha = br.readLine();
            while(linha != null){
                str= new StringTokenizer (linha, "/");
                nome=str.nextToken();
                apelido=str.nextToken();
                bi=str.nextToken();
                genero=str.nextToken();
                endereco=str.nextToken();
                nacionalidade=str.nextToken();
                estadoCivil=str.nextToken();
                dataNasc=str.nextToken();
                telefone=Long.parseLong(str.nextToken());
                codFunc=Integer.parseInt(str.nextToken());
                nuit=Integer.parseInt(str.nextToken());
                dataRegistro=str.nextToken();
                
                criterio = str.nextToken().charAt(0);
                if (criterio=='A' || criterio== 'a'){
                    cargo =str.nextToken();
                    criaAdmin (nome, apelido, bi, codFunc, genero, endereco, nacionalidade, estadoCivil, dataNasc,telefone,  nuit,dataRegistro,salario, cargo);
                }
                else{
                    if(criterio == 'E' || criterio == 'e'){
                        areaForm = str.nextToken();
                        areaLeciona = str.nextToken();
                        criterio = str.nextToken().charAt(0);
                        if(criterio == 'L' || criterio == 'l'){
                            anosExperiencia = Byte.parseByte(str.nextToken());
                            anoDeFormacao = Integer.parseInt(str.nextToken());
                            criaLicenciado(nome, apelido, bi, codFunc, genero, endereco, nacionalidade, estadoCivil, dataNasc,telefone, nuit, dataRegistro, salario,areaForm, areaLeciona, anosExperiencia, anoDeFormacao);
                        }
                        else{
                            if(criterio == 'E' || criterio =='e'){
                                nivelEscolar = Integer.parseInt(str.nextToken());
                                instituicao = str.nextToken();
                                criaEstudante(nome, apelido,bi, codFunc, genero, endereco, nacionalidade, estadoCivil, dataNasc,telefone,nuit, dataRegistro, salario,areaForm, areaLeciona, nivelEscolar, instituicao);
                            }
                        }
                    }
                }
                linha = br.readLine();
            }
            br.close();
        }
        catch(FileNotFoundException fn){
            System.out.println("Ficheiro não econtrado");
        }
        catch(NumberFormatException nn){
            System.out.println(nn.getMessage());
        }
        catch(IOException io){
            System.out.println(io.getMessage());
        }
    }
    public void criaAdmin (String nome, String apelido, String bi,int codFunc, String genero, String endereco, String nacionalidade, String estadoCivil, String dataNasc,long telefone, int nuit,String dataRegistro, float salario,String cargo) {
        Admin a= new Admin ();
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
    }
    public void criaEstudante(String nome, String apelido, String bi,int codFunc, String genero, String endereco, String nacionalidade, String estadoCivil, String dataNasc,long telefone, int nuit,String dataRegistro,float salario,String areaForm,String areaLeciona,int nivelEscolar, String instituicao) {
        Estudante e = new Estudante();
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
    }
    public String toString(){
        String ver="";
        Funcionario f;
        for(int i = 0; i < vt.size(); i++){
            f = (Funcionario)vt.elementAt(i);
            ver+= ver.concat(f.toString() + "\n");
        }
        return ver;
    }
    public void cadastro() throws IOException{
        v=new Validacoes();
        nome=v.validarNome("Nome(no minimo 3 letras e no maximo 20)",3,20);
        apelido=v.validarNome("Apelido (Deve conter no minimo 3 letras e no maximo 20 e sem espaco)",3,20);
        bi=v.validarBI("BI Deve conter 12 numeros e uma letra",(byte)13);
        codFunc=v.validarInt("Codigo do funcionario: (Com 5 digitos)", 10000, 99999);
        genero=v.adapatarOpcoes(" Sexo: 1-Femenio 2-Masculino ", "F" , "M");
        System.out.println("Introduz o seu actual endereço: ");
        endereco = br.readLine();
        nacionalidade=v.validarSemNr("Nacionalidade", (byte) 5, (byte)20);
        estadoCivil=v.validarEstCivil();
        dataNasc =  v.validarData(" de Nascimento ", 1950, 2001);
        telefone = v.validarLong(" Número de telefone ", 820000000, 870000000);
        nuit = v.validarInt(" NUIT ",100000000 , 999999999);
        dataRegistro = v.dataActual();
        s.menuSal();
        this.salario = s.getSalario();
        criterio = v.adapatarOpcoes(" Fuçao: 1-Administração 2-Explicação ", "A", "E").charAt(0);
        if(criterio == 'A' || criterio == 'a'){
            cargo = v.validarSemNr(" Cargo", 5, 30);
            criaAdmin (nome, apelido, bi, codFunc, genero, endereco, nacionalidade, estadoCivil, dataNasc,telefone,  nuit,dataRegistro,salario, cargo);
        }
        else{
            if(criterio == 'E' || criterio == 'e' ){
                areaForm = v.validarSemNr(" Curso ", 3, 15);
                areaLeciona = v.validarSemNr(" Area que Lecciona ",5, 15);
                criterio = v.adapatarOpcoes(" Nível de Formação: 1-Estudante 2-Licenciado ", "E", "L").charAt(0);
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
    }
    public void escreverFicheiroTXT(){
        try{
            FileWriter fw=new FileWriter("funcionarios.txt",true);
            BufferedWriter bw=new BufferedWriter(fw);
            for(int i=0; i<vt.size(); i++){
                fun=(Funcionario)(vt.elementAt(i));
                bw.write(fun.dadosFich());
                bw.newLine();
            }
            bw.close();
        }catch(IOException io){System.out.print(io.getMessage());}        
    }
    public void escreverFicheiroOdj(String fichOd){
        try{
            FileOutputStream fos= new FileOutputStream(fichOd);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(vt);
            oos.close();
        }catch(IOException ios){System.out.print(ios.getMessage());}
    }
}