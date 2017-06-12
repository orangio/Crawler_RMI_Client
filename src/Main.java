/**
 * Created by Filip on 14.03.2017.
 */

import crawler.*;

import crawler.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;


public class Main extends Application{
    private static String[] arguments;
    private MainController controller;
    public static void main( String[] args ) throws Exception
    {
        arguments = args;
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) throws RemoteException, NotBoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("crawler/xmlfs/main.fxml"));

        try
        {
            loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        controller=loader.getController();

        primaryStage.setTitle("Crawler");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(600);
        primaryStage.show();

        String name = "rmi://" + 999 + "/CrawlerProxy";

        Registry registry = LocateRegistry.getRegistry("localhost", 999);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CrawlerMethodsInterface crawlerServer = (CrawlerMethodsInterface & Serializable) registry.lookup(name);
                    TmpListener list = new TmpListener();
                    list.setTabHController(controller.getTabHController());
                    list.setTabLController(controller.getTabLController());
                    list.setTabSController(controller.getTabSController());


                    /*
                    class TmpListener2 extends UnicastRemoteObject implements CrawlerEventListener{

                        protected TmpListener2() throws RemoteException{
                            super();
                        }

                        @Override
                        public void onStudentAdded(Student a) throws RemoteException {

                        }

                        @Override
                        public void onStudentDeleted(Student a) throws RemoteException {

                        }

                        @Override
                        public void onNoChange() throws RemoteException {

                        }
                    }
                    */

                    crawlerServer.addListener(list);
                    crawlerServer.run();
                } catch (Exception e ) {
                    e.printStackTrace();
                }
            }
        })).start();
        /*


        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("crawler/xmlfs/main.fxml"));

        try
        {
            loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        controller=loader.getController();

        primaryStage.setTitle("Crawler");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(600);
        primaryStage.show();
        return null;
            }
        };

        try {
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error");
        }
        String name="rmi://"+999+"/CrawlerProxy";
        Registry registry = LocateRegistry.getRegistry("localhost",999);
        CrawlerMethodsInterface crawlerProxy = (CrawlerMethodsInterface) registry.lookup(name);
        crawlerProxy.addListener(controller);
        crawlerProxy.run();
        */
    }


    /*
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

       MAIN NA POTRZEBY projektu używającego javafx

       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


     @Override
    public void start(Stage primaryStage) throws Exception {
        Crawler c1=new Crawler();
        MailLogger mailLog= new MailLogger();
        ConsoleLogger conLog=new ConsoleLogger();


        Scene scene = new Scene(new VBox(), 400, 400);

        //CustomMenuBar customBar = new CustomMenuBar();
        MenuBar customBar = new MenuBar();

        //CustomTabPane tabPane =new CustomTabPane();
        TabPane tabPane = new TabPane();


        ((VBox) scene.getRoot()).setVgrow(tabPane,Priority.ALWAYS);
        ((VBox)scene.getRoot()).getChildren().addAll(customBar,tabPane);

        scene.setFill(Color.BLUE);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(550);
        primaryStage.show();


        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                c1.addListener(conLog);
                c1.addListener(tabPane);
                c1.addListener(mailLog);
                c1.run();
                return null;
            }
        };
        try {
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error");
        }
        */
    }

