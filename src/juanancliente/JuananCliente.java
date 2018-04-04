/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juanancliente;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import servlet.JAXBException_Exception;
import servlet.Persona;
import servlet.ServiciosBasicos_Service;
import servlet.ValidarXSDDTD_Service;



/**
 *
 * @author janto
 */
public class JuananCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        ValidarXSDDTD_Service service = new ValidarXSDDTD_Service();
         boolean correoCorrecto = false;
         ServiciosBasicos_Service service2 = new ServiciosBasicos_Service();

        while (!salir) {

            System.out.println("1. Validar XSD");
            System.out.println("2. Validar DTD");
            System.out.println("3. Mostrar Agenda");
            System.out.println("4. Mostrar una Persona");
            System.out.println("5. Añadir Persona");
            System.out.println("6. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:

                        String a = service.getValidarXSDDTDPort().validarXSD();
                        System.out.println(a);

                        break;
                    case 2:

                        String b = service.getValidarXSDDTDPort().validarDTD();
                        System.out.println(b);

                        break;

                    case 3:
//                        ServiciosBasicos_Service service2 = new ServiciosBasicos_Service();
                        try {
                            
                            servlet.Agenda ag = service2.getServiciosBasicosPort().mostrarAgenda();
                            for (int i = 0; i < ag.getPersona().size(); i++) {
                                System.out.println(ag.getPersona().get(i).getName());
                                System.out.println(ag.getPersona().get(i).getEmail());
                                System.out.println(ag.getPersona().get(i).getTelephone());
                                System.out.println("---------------------");
                            }
                        } catch (JAXBException_Exception ex) {
                            Logger.getLogger(JuananCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        break;
                    case 4:
                       //if no existe null
                         Scanner sc1 = new Scanner(System.in);
                         String name1 = "";
                          System.out.print("Ingrese el nombre a buscar: ");
                         name1 = sc1.nextLine();
                         
                        servlet.Persona ag2 = service2.getServiciosBasicosPort().mostarPersona(name1);
                        if(ag2!=null){
                        System.out.println("Name:" + ag2.getName());
                        System.out.println("Email:" + ag2.getEmail());
                        System.out.println("Telephone:" + ag2.getTelephone());
                        }else{
                            System.out.println("El usuario: " + name1 + " " + "no se encuentra en la agenda");
                        }
                        
                        break;

                    case 5:
                        Scanner sc = new Scanner(System.in);
                        servlet.Persona p = new Persona();

                        String name = "";
                        String email = "";
                        int telephone = 0;

                        System.out.print("Ingrese su nombre: ");
                        name = sc.nextLine();

                        while (!correoCorrecto) {

                            System.out.print("Ingrese su correo: ");
                            email = sc.nextLine();
                            if (email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                                correoCorrecto = true;
                            } else {
                                System.out.println("Correo no correcto");
                            }
                        }
                        System.out.print("Ingrese la numero de telefono: ");
                        telephone = sc.nextInt();
                        p.setName(name);
                        p.setEmail(email);
                        p.setTelephone(telephone);
                        
                        //hacer global
                       
                        service2.getServiciosBasicosPort().crearContacto(p);
                        break;
                        
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 9");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            } catch (JAXBException_Exception ex) {
                Logger.getLogger(JuananCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
}
