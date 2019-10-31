package sgrh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

 public class Operacoes {
   private Vector vs; Funcionario f,f1;
   private Tarefas tr=new Tarefas();
   private int op;
   private Validacoes vr;
   private Adpt_Ficheiro ficheiro;
   //Construtor
   public Operacoes(){ vs= new Vector();vr=new Validacoes();ficheiro = new  Adpt_Ficheiro();}
   public void lerFich (String nomeFich){
        //Variaveis
            String nome, apelido, bi, genero, endereco, nacionalidade, estadoCivil, dataNasc,dataRegistro, areaForm, areaLeciona,instituicao,cargo;
            long telefone;
            int codFunc, nuit,nivelEscolar,anoDeFormacao;
            byte anosExperiencia;
            float salario=0;
            char criterio;
         
        StringTokenizer str;
        String linha = "";
        try{
            BufferedReader br = new BufferedReader (new FileReader(nomeFich));
            linha = br.readLine();
            while(linha != null){
                str= new StringTokenizer (linha, "/");
                codFunc=Integer.parseInt(str.nextToken());
                nome=str.nextToken();
                apelido=str.nextToken();
                bi=str.nextToken();
                genero=str.nextToken();
                endereco=str.nextToken();
                nacionalidade=str.nextToken();
                estadoCivil=str.nextToken();
                dataNasc=str.nextToken();
                telefone=Long.parseLong(str.nextToken());
                nuit=Integer.parseInt(str.nextToken());
                dataRegistro=str.nextToken();
                salario=Float.parseFloat(str.nextToken());
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
   public void menuActualizacao() throws IOException, ClassNotFoundException{
        byte op=0,posicao;
        //>>>>>>>>>>>>>>> Menu
        System.out.println("--------------------------------------------------------------- Lista de Todos Funcionarios  --------------------------------------------");
        System.out.println(toString());
        posicao=encontrarIndice();
       //>>>>>>>>>>>>>> Cases
        f=(Funcionario)vs.elementAt(posicao);
        Explicador ex; Admin ad;Estudante es;
        do{ 
           System.out.println("----------------------------- Menu de Actualizacão  ---------------------------");
           op=vr.validarByte("O que deseja actualizar: \n 1 - Estado Civil \n 2 - Endereço  \n 3 - Contacto \n 4 - Cargo (Somente para funcionarios do Corpo adminstrativo) \n 5 - Area Em Que Leciona (Somente para funcionarios que seijam Explicadores) \n 6 - Instituicão (Somente para funcionarios que seijam Explicadores e Estudantes)\n 0 - Sair",0,6);
           switch(op){
            case 1:
             f.setEstadoCivil(vr.validarEstCivil());
             vs.remove(posicao);
             vs.add(posicao,f); 
            break;
            case 2:
              f.setEndereco(vr.NotValid("Introduz o seu actual endereço"));
              vs.remove(posicao);
              vs.add(posicao,f);
            break;
            case 3:
             f.setTelefone(vr.validarLong(" Número de telefone ", 820000000, 870000000));
             vs.remove(posicao);
             vs.add(posicao,f);
            break;
            case 4:
             if(f instanceof Admin ){
             ad=(Admin)vs.elementAt(posicao);
             ad.setCargo(vr.adaptarCargo());
             vs.remove(posicao);
             vs.add(posicao,ad);
             ad=(Admin)vs.elementAt(posicao);

             }else{
                 System.out.print("Nao pode realizar essa Opeacao");
             }
            break;
            case 5:
               if(f instanceof Explicador ){
             ex=(Explicador)vs.elementAt(posicao);
             ex.setAreaLeciona(vr.validarSemNr(" Area que Lecciona ",5, 15));
             vs.remove(posicao);
             vs.add(posicao,ex);
             }else{
                 System.out.println("-----------------------------\n Nao pode realizar essa Opeacao \n -----------------------------");
             }
            break;
            case 6:
             if(f instanceof Estudante ){
             es=(Estudante)vs.elementAt(posicao);
             es.setAreaLeciona(vr.validarSemNr(" Area que Lecciona ",5, 15));
             vs.remove(posicao);
             vs.add(posicao,es);
             }else{
                 System.out.print("Nao pode realizar essa Opeacao");
             } 
            break;
            default:
            break;}
           vs.trimToSize();
        }while(op!=0);
      ficheiro.esc_VectorEmTXT("funcionarios", vs,false);  
    }
   public void ordenarNome(){
        String nomedef1,nome[]=new String[2];
        int ind;
        ind=vs.size();
     for(int i=0; i<ind;i++){
           for(int j=0;j<ind-2;j++){
               
                f=(Funcionario)vs.elementAt(j);
                nome[0]=f.getNome();
                
                f1=(Funcionario)vs.elementAt(j+1);
                nome[1]=f1.getNome();
                
                nomedef1=f.getNome();
                
                Arrays.sort(nome);
                if(nomedef1.equalsIgnoreCase(nome[0])){
                   vs.remove(j);vs.remove(j);
                   vs.add(j,f);
                   vs.add(j+1,f1);vs.trimToSize();
             }else{
                   vs.remove(j+1);vs.remove(j);
                   vs.add(j,f1);vs.add(j+1,f);vs.trimToSize();}
         }
           
     }
    
    }
    
    public void Remover()throws IOException, ClassNotFoundException{
    int indice=0,cod;
    byte x=0;
      indice=encontrarIndice();
      if(indice!=-1){
      vs.remove(indice);
      vs.trimToSize();}
    }
    public byte encontrarIndice()throws IOException, ClassNotFoundException{
    int cod;
    byte x=-1,y=0,c=0;
    do{
        cod=vr.validarInt("Codigo(no formato xxxxx)", 11111, 99999);
        for(byte i=0; i<vs.size();i++){
            f=(Funcionario)vs.elementAt(i);
            if(f.getCodFunc()==cod){ x=i;y=0;c=0;}    
         }
        if(c==-1){c=-1; y=vr.validarByte("Funcionario Nao Encontrado! \n1 - Tentar Novamente \n0 - Cancelar", (byte)0, (byte)1);}
    }while(y==1);
    return x;
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
        vs.addElement(a);
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
        vs.addElement(l);
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
        vs.addElement(e);
        
    }
     //Escrita No Ficheiro
    public void escreverFicheiroTXT(String nomeFich){ficheiro.esc_VectorEmTXT(nomeFich, vs, true); };
    public void escreverFicheiroDAT(String nomeFich){ ficheiro.esc_VectorEmDAT(nomeFich, vs);
    };
    public String toString(){
        String ver="";
        Funcionario f;
        for(int i = 0; i < vs.size(); i++){
            f = (Funcionario)vs.elementAt(i);
            ver+= (f.toString() + "\n");
        }
        return ver;
    }
}
