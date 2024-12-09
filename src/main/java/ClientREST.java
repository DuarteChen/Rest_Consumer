import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;



// CXF REST Client Invoking POST Method
public class ClientREST {
    public static void main(String[] args) {
    	
    	
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("1. Login");
        System.out.println("2. registar");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após a entrada do número
        String clientIDString = "0";

        if (choice == 1) {
            // Lógica de login
            while (clientIDString.equals("0")) {
                System.out.println("Insira o user name: ");
                String username = scanner.nextLine(); // Entrada do username
                System.out.println("Insira a palavra passe: ");
                String pass = scanner.nextLine(); // Entrada da senha
                
                
                
                String response = "0";
                
                try {
                    URL url = new URL("http://localhost:8080/CD_FrontEnd_Rest/rest/autenticar");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");

                    String input = "" + username.trim()+";"+pass.trim();

                    OutputStream os = conn.getOutputStream();
                    os.write(input.getBytes());
                    os.flush();

                    Scanner responseScanner = new Scanner(conn.getInputStream());
                    response = responseScanner.useDelimiter("\\Z").next();  // Read entire response
                    responseScanner.close();

                } catch (MalformedURLException e) {
                    //e.printStackTrace();
                    System.out.println("Erro de autenticação.");
                } catch (IOException e) {
                    //e.printStackTrace();
                    System.out.println("Erro de autenticação.");
                }
                
                
                
                clientIDString = response;
                if (clientIDString.equals("0")) {
                    System.out.println("Credenciais erradas. Tente novamente.");
                }
                
                
                
            }
            System.out.println("Login bem-sucedido! ID do cliente: " + clientIDString);
        } else if (choice == 2) {
            // Lógica de registo
            String registado = "Utilizador já existe!";
            while (registado.equals("Utilizador já existe!")) {
                System.out.println("Insira o user name: ");
                String username = scanner.nextLine(); // Entrada do username
                System.out.println("Insira a palavra passe a definir: ");
                String pass = scanner.nextLine(); // Entrada da senha

                
                String response = "Utilizador já existe!";
                
                
                
                try {
                    URL url = new URL("http://localhost:8080/CD_FrontEnd_Rest/rest/registar");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");

                    String input = "" + username.trim()+";"+pass.trim();

                    OutputStream os = conn.getOutputStream();
                    os.write(input.getBytes());
                    os.flush();

                    Scanner responseScanner = new Scanner(conn.getInputStream());
                    response = responseScanner.useDelimiter("\\Z").next();  // Read entire response
                    System.out.println(response);
                    responseScanner.close();

                } catch (MalformedURLException e) {
                    //e.printStackTrace();
                	System.out.println("Erro a registar.");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erro a registar.");
                }
                
                
                registado = response;
                if (registado.equals("Utilizador já existe!")) {
                    System.out.println("Erro: Utilizador já existe! Tente novamente.");
                }
            }
            System.out.println("Registo bem-sucedido! Por favor, faça login.");

            // Lógica de login após registro
            while (clientIDString.equals("0")) {
                System.out.println("Insira o user name: ");
                String username = scanner.nextLine(); // Entrada do username
                System.out.println("Insira a palavra passe: ");
                String pass = scanner.nextLine(); // Entrada da senha

                
                
                
                
            	String response = "0";
                
                try {
                    URL url = new URL("http://localhost:8080/CD_FrontEnd_Rest/rest/autenticar");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");

                    String input = "" + username.trim()+";"+pass.trim();

                    OutputStream os = conn.getOutputStream();
                    os.write(input.getBytes());
                    os.flush();

                    Scanner responseScanner = new Scanner(conn.getInputStream());
                    response = responseScanner.useDelimiter("\\Z").next();  // Read entire response
                    System.out.println(response);
                    responseScanner.close();

                } catch (MalformedURLException e) {
                    //e.printStackTrace();
                	System.out.println("Erro de autenticação.");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erro de autenticação.");
                }
                
                
                
                
                
                
                
                
                clientIDString = response;
                if (clientIDString.equals("0")) {
                    System.out.println("Credenciais erradas. Tente novamente.");
                }
            }
            
            
            
            
            
            
            System.out.println("Login bem-sucedido! ID do cliente: " + clientIDString);
        } else {
            System.out.println("Opção inválida! Por favor, escolha 1 (Login) ou 2 (Registar).");
        }

        
        Integer clientID = 0;

        try {
            clientID = Integer.valueOf(clientIDString);  // Converts String to Integer
            //System.out.println("clientIdAsInteger: " + clientID);  // Prints the Integer object
        } catch (NumberFormatException e) {
            //System.out.println("Invalid number format: " + clientIDString);  // Catches invalid format cases
        }

        
        
      
        
        while (clientID != 0) {
            System.out.println("\n--- Client REST Menu ---");
            System.out.println("1. Listar Consultas");
            System.out.println("2. Marcar consulta");
            System.out.println("3. Cancelar consulta");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opcao: ");
            choice = scanner.nextInt();  // Read menu choice
            scanner.nextLine();  // Consume the newline character after nextInt()
            
            switch (choice) {
                case 1:
                    try {
                        URL url = new URL("http://localhost:8080/CD_FrontEnd_Rest/rest/listarConsultas");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json");

                        String input = "" + clientID;

                        OutputStream os = conn.getOutputStream();
                        os.write(input.getBytes());
                        os.flush();

                        Scanner responseScanner = new Scanner(conn.getInputStream());
                        String response = responseScanner.useDelimiter("\\Z").next();  // Read entire response
                        System.out.println(response);
                        responseScanner.close();

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        URL url = new URL("http://localhost:8080/CD_FrontEnd_Rest/rest/marcarConsulta");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json");

                        //  String marcarConsulta(int dia, int mes, int ano, int hora, int clientID, int clinicaID, int especialidadeID) throws RemoteException;
                        System.out.println("Insira o dia: ");
                        String input = "" + scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Insira o mes: ");
                        input += ";" + scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Insira o ano: ");
                        input += ";" + scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Insira a hora: ");
                        input += ";" + scanner.nextInt();
                        scanner.nextLine();
                       
                        
                        input += ";" + clientID; //ID do cliente aparece nesta posição de argumentos no backend
                        
                        
                        //Para listar as clinicas
                        chamarListarClinicas();
                        System.out.println("Insira o ID da clinica: ");
                        String idClinica = "" + scanner.nextInt();
                        input += ";" + idClinica;
                        scanner.nextLine();
                        
                        
                        
                        //Para listar as especilidades
                        chamarListarEspecialidades(idClinica);
                        System.out.println("Insira o ID da especialidade: ");
                        input += ";" + scanner.nextInt();
                        scanner.nextLine();

                        
                        
                        
                        
                        
                        OutputStream os = conn.getOutputStream();
                        os.write(input.getBytes());
                        os.flush();

                        Scanner responseScanner = new Scanner(conn.getInputStream());
                        String response = responseScanner.useDelimiter("\\Z").next();  // Read entire response
                        System.out.println(response);
                        responseScanner.close();

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                    // Code for marking consultation
                    
                case 3:
                	
                	chamarListarConsultas(clientID);
                	
                	System.out.println("Insira o número Consulta a cancelar: ");
                    int idConsulta = scanner.nextInt();
                	
                	
                    chamarRemoverConsulta(idConsulta, clientID);
                	
                	
                    // Code for canceling consultation
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();  // Close the scanner only when exiting the program
                    return;
                default:
                    System.out.println("Opcao inválida. Tente novamente.");
                    break;
            }
        }
    }
   
    
 // Method to list clinics
    private static void chamarListarClinicas() {
        try {
            URL url = new URL("http://localhost:8080/CD_FrontEnd_Rest/rest/listarClinicas");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            try (Scanner clinicScanner = new Scanner(
                conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream())) {
                String response = clinicScanner.useDelimiter("\\Z").next();
                System.out.println(response);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            System.err.println("URL malformada: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de comunicação: " + e.getMessage());
        }
    }
		
    
    // Method to list Especialidades
    private static void chamarListarEspecialidades(String idClinica) {
    	try {
            URL url = new URL("http://localhost:8080/CD_FrontEnd_Rest/rest/listarEspecialidades");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = idClinica;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            Scanner responseScanner = new Scanner(conn.getInputStream());
            String response = responseScanner.useDelimiter("\\Z").next();  // Read entire response
            System.out.println(response);
            responseScanner.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private static void chamarListarConsultas(int idClient) {
    	try {
            URL url = new URL("http://localhost:8080/CD_FrontEnd_Rest/rest/listarConsultas");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            
            String idClientString = "" + idClient;
            String input = idClientString;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            Scanner responseScanner = new Scanner(conn.getInputStream());
            String response = responseScanner.useDelimiter("\\Z").next();  // Read entire response
            System.out.println(response);
            responseScanner.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		
    private static void chamarRemoverConsulta(int idConsulta, int clientID) {
    	try {
            URL url = new URL("http://localhost:8080/CD_FrontEnd_Rest/rest/removerConsulta");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            
            String idClientString = idConsulta + ";" + clientID;
            String input = idClientString;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            Scanner responseScanner = new Scanner(conn.getInputStream());
            String response = responseScanner.useDelimiter("\\Z").next();  // Read entire response
            System.out.println(response);
            responseScanner.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		
    	
    }
