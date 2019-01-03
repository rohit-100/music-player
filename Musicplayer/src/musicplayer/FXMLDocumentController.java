/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.awt.GridBagLayout;

import java.io.File;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.*;
import java.util.*;
import java.io.*;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javax.swing.JButton;
import javafx.scene.media.*;
import javafx.util.Duration;
import javax.swing.JPanel;
import static musicplayer.searching.RecursivePrint;
import musicplayer.searching;
import static musicplayer.searching.i;
import static musicplayer.searching.musicname;
import static musicplayer.searching.musicplayer;
import javafx.scene.Scene.*;
import javafx.scene.control.TextField;



/**
 *
 * @author LENOVO-PC
 */
public class FXMLDocumentController implements Initializable
{
   
    //ObservableList<String> observablelist =FXCollections.observableArrayList("kunal","manish");
    private String filepath;
    private MediaPlayer mediaplayer;
    private int j = 0;
    public double rate = 1;
    
     List<String> list = new ArrayList<String>(){{
        //add("*.mp3");
        //add("*.mp4");        
    }};
     //String k = new String("nknk");
     //list.add(k);

     @FXML
     private VBox playlist;
     @FXML
     private ChoiceBox choicebox;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> combobox;
    @FXML
     private ComboBox<String> bookmark;
    @FXML
     private ComboBox<String> recentlyplayed;
     @FXML
     private ComboBox<String> playlist1;
     @FXML
     private ComboBox<String> playlist2;
     @FXML
     private ComboBox<String> playlist3;
     @FXML
     private ComboBox<String> playlist4;
     //@FXML
    // private ComboBox<String> playlist5;
    @FXML
    private Slider slider;
    @FXML
    private TextField searchfield;
    @FXML
    private MediaView mediaview;
    @FXML
    private Slider seeSlider;
    
    
    int i =0;
    public String music;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(j == 1)
        mediaplayer.stop();
        //FileChooser fileChooser = new FileChooser();
        //FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("gygy",list1);
       // FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("m file please","*.mp3");
        //fileChooser.getExtensionFilters().add(filter);
        music = combobox.getValue().toString();
        i = combobox.getSelectionModel().getSelectedIndex();
        
        //File file = fileChooser.showOpenDialog(null);
        for(int k = 0;k<musicplayer.length;k++)
        if(musicplayer[k].getName().equals(music))
        {filepath = musicplayer[k].toURI().toString();
        break;
        }
        
        if(filepath!=null)
        {
            Media media = new Media(filepath);
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            
            DoubleProperty width = mediaview.fitWidthProperty();
            DoubleProperty higth = mediaview.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaview.sceneProperty(),"width"));
            higth.bind(Bindings.selectDouble(mediaview.sceneProperty(),"higth"));
        
            slider.setValue(mediaplayer.getVolume()*100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(javafx.beans.Observable observable) {
                    mediaplayer.setVolume(slider.getValue()/100);
//To change body of generated methods, choose Tools | Templates.
                }
            }
            );
            
            mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 //To change body of generated methods, choose Tools | Templates.
                seeSlider.setValue(newValue.toSeconds());
                
                }
            }
            );
            
           
            seeSlider.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    mediaplayer.seek(Duration.seconds(seeSlider.getValue()));
                }
                
            });
            mediaplayer.play();
           // mediaplayer.setVolume(slider.getValue()/100);
              
            
            j=1;
             recentlyplayed.getItems().add(music);
        }
    }
    ObservableList<String> list1 = FXCollections.observableArrayList(list);
 ObservableList<String> list2 = FXCollections.observableArrayList(list);
  ObservableList<String> list3 = FXCollections.observableArrayList(list);
 ObservableList<String> list4 = FXCollections.observableArrayList(list);
 ObservableList<String> list5 = FXCollections.observableArrayList(list);
  ObservableList<String> list6 = FXCollections.observableArrayList(list);
 ObservableList<String> list7 = FXCollections.observableArrayList(list);
 ObservableList<String> list8 = FXCollections.observableArrayList(list);
  ObservableList<String> list9 = FXCollections.observableArrayList(list);//list.add("lksk");
    //list.add("njn");
    @FXML
    private void pause(ActionEvent event) {
        mediaplayer.pause();
    }
    
    @FXML
    private void stop(ActionEvent event) {
    mediaplayer.stop();}
    
    @FXML
    private void fast(ActionEvent event) {
    mediaplayer.setRate(rate*1.25);
    rate=rate*1.25;
    }
    
    @FXML
    private void faster(ActionEvent event) {
        mediaplayer.setRate(rate*1.5);
        rate=rate*1.5;
    }
    
    @FXML
    private void play(ActionEvent event) {
        mediaplayer.play();
    }
    
    @FXML
    private void slow(ActionEvent event) {
        mediaplayer.setRate(rate*0.75);
        rate=rate*0.75;
    }
    
    @FXML
    private void slower(ActionEvent event) {
    mediaplayer.setRate(rate*0.5);
    rate=rate*0.5;}
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
   @FXML
    private void next(ActionEvent event) {
        mediaplayer.stop();
        music = combobox.getItems().get(++i).toString();
       // System.out.println(musicnext);
         for(int k = 0;k<musicplayer.length;k++)
        if(musicplayer[k].getName().equals(music))
        {filepath = musicplayer[k].toURI().toString();
        break;
        }
        
        if(filepath!=null)
        {
            Media media = new Media(filepath);
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            
            DoubleProperty width = mediaview.fitWidthProperty();
            DoubleProperty higth = mediaview.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaview.sceneProperty(),"width"));
            higth.bind(Bindings.selectDouble(mediaview.sceneProperty(),"higth"));
        
            slider.setValue(mediaplayer.getVolume()*100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(javafx.beans.Observable observable) {
                    mediaplayer.setVolume(slider.getValue()/100);
//To change body of generated methods, choose Tools | Templates.
                }
            }
            );
            
            mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 //To change body of generated methods, choose Tools | Templates.
                seeSlider.setValue(newValue.toSeconds());
                
                }
            }
            );
            
           
            seeSlider.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    mediaplayer.seek(Duration.seconds(seeSlider.getValue()));
                }
                
            });
            mediaplayer.play();
           // mediaplayer.setVolume(slider.getValue()/100);
              
             recentlyplayed.getItems().add(music);
            j=1;}
    }
    
    @FXML
    private void prev(ActionEvent event) {
        mediaplayer.stop();
        music = combobox.getItems().get(++i).toString();
       // System.out.println(musicnext);
         for(int k = 0;k<musicplayer.length;k++)
        if(musicplayer[k].getName().equals(music))
        {filepath = musicplayer[k].toURI().toString();
        break;
        }
        
        if(filepath!=null)
        {
            Media media = new Media(filepath);
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            
            DoubleProperty width = mediaview.fitWidthProperty();
            DoubleProperty higth = mediaview.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaview.sceneProperty(),"width"));
            higth.bind(Bindings.selectDouble(mediaview.sceneProperty(),"higth"));
        
            slider.setValue(mediaplayer.getVolume()*100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(javafx.beans.Observable observable) {
                    mediaplayer.setVolume(slider.getValue()/100);
//To change body of generated methods, choose Tools | Templates.
                }
            }
            );
            
            mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 //To change body of generated methods, choose Tools | Templates.
                seeSlider.setValue(newValue.toSeconds());
                
                }
            }
            );
            
           
            seeSlider.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    mediaplayer.seek(Duration.seconds(seeSlider.getValue()));
                }
                
            });
            mediaplayer.play();
           // mediaplayer.setVolume(slider.getValue()/100);
              recentlyplayed.getItems().add(music); 
            
            j=1;}
    }
    
    @FXML
    private void playbk(ActionEvent event) {
        mediaplayer.stop();
        music = bookmark.getValue().toString();
       // System.out.println(musicnext);
         for(int k = 0;k<musicplayer.length;k++)
        if(musicplayer[k].getName().equals(music))
        {filepath = musicplayer[k].toURI().toString();
        break;
        }
        
        if(filepath!=null)
        {
            Media media = new Media(filepath);
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            
            DoubleProperty width = mediaview.fitWidthProperty();
            DoubleProperty higth = mediaview.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaview.sceneProperty(),"width"));
            higth.bind(Bindings.selectDouble(mediaview.sceneProperty(),"higth"));
        
            slider.setValue(mediaplayer.getVolume()*100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(javafx.beans.Observable observable) {
                    mediaplayer.setVolume(slider.getValue()/100);
//To change body of generated methods, choose Tools | Templates.
                }
            }
            );
            
            mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 //To change body of generated methods, choose Tools | Templates.
                seeSlider.setValue(newValue.toSeconds());
                
                }
            }
            );
            
           
            seeSlider.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    mediaplayer.seek(Duration.seconds(seeSlider.getValue()));
                }
                
            });
            mediaplayer.play();
           // mediaplayer.setVolume(slider.getValue()/100);
              
             recentlyplayed.getItems().add(music);
             
            j=1;}
    }
    
    @FXML
    private void playrp(ActionEvent event) {
        mediaplayer.stop();
        music = recentlyplayed.getValue().toString();
       // System.out.println(musicnext);
         for(int k = 0;k<musicplayer.length;k++)
        if(musicplayer[k].getName().equals(music))
        {filepath = musicplayer[k].toURI().toString();
        break;
        }
        
        if(filepath!=null)
        {
            Media media = new Media(filepath);
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            
            DoubleProperty width = mediaview.fitWidthProperty();
            DoubleProperty higth = mediaview.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaview.sceneProperty(),"width"));
            higth.bind(Bindings.selectDouble(mediaview.sceneProperty(),"higth"));
        
            slider.setValue(mediaplayer.getVolume()*100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(javafx.beans.Observable observable) {
                    mediaplayer.setVolume(slider.getValue()/100);
//To change body of generated methods, choose Tools | Templates.
                }
            }
            );
            
            mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 //To change body of generated methods, choose Tools | Templates.
                seeSlider.setValue(newValue.toSeconds());
                
                }
            }
            );
            
           
            seeSlider.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    mediaplayer.seek(Duration.seconds(seeSlider.getValue()));
                }
                
            });
            mediaplayer.play();
           // mediaplayer.setVolume(slider.getValue()/100);
              recentlyplayed.getItems().add(music); 
            
            j=1;}
    }
    
   
    @FXML
    private void search(ActionEvent event) 
    {
       // mediaplayer.stop();
        String str = searchfield.getText();
        
     //   System.out.println(str);
        //music = combobox.getItems().get(++i).toString();
       // System.out.println(musicnext);
         for(int k = 0;k<musicplayer.length;k++)
        if(musicplayer[k].getName().contains(str))
        {filepath = musicplayer[k].toURI().toString();
        music=musicplayer[k].getName();
        break;
        }
        // System.out.println(filepath);
        if(j==1)
         mediaplayer.stop();
        
        if(filepath!=null)
        {
            Media media = new Media(filepath);
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            
            DoubleProperty width = mediaview.fitWidthProperty();
            DoubleProperty higth = mediaview.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaview.sceneProperty(),"width"));
            higth.bind(Bindings.selectDouble(mediaview.sceneProperty(),"higth"));
        
            slider.setValue(mediaplayer.getVolume()*100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(javafx.beans.Observable observable) {
                    mediaplayer.setVolume(slider.getValue()/100);
//To change body of generated methods, choose Tools | Templates.
                }
            }
            );
            
            mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 //To change body of generated methods, choose Tools | Templates.
                seeSlider.setValue(newValue.toSeconds());
                
                }
            }
            );
            
           
            seeSlider.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    mediaplayer.seek(Duration.seconds(seeSlider.getValue()));
                }
                
            });
            mediaplayer.play();
           // mediaplayer.setVolume(slider.getValue()/100);
              recentlyplayed.getItems().add(music);
              
            
            j=1;}
    }
   
    
    @FXML
    private void bookmark(ActionEvent event) throws ClassNotFoundException, SQLException{
       bookmark.getItems().add(music); 
       Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/avishkar","root","mtr281001");
         PreparedStatement stmt7=con.prepareStatement("insert into bookmarks(musics) values(?)");
         stmt7.setString(1,music);
          stmt7.executeUpdate();
    }
    
     @FXML
    private void playlist1(ActionEvent event) throws ClassNotFoundException, SQLException{
       playlist1.getItems().add(music);
       Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/avishkar","root","mtr281001");
         PreparedStatement stmt7=con.prepareStatement("insert into plsylist1 values(?)");
         stmt7.setString(1,music);
          stmt7.executeUpdate();
    }
    
    @FXML
    private void playlist2(ActionEvent event) throws ClassNotFoundException, SQLException{
       playlist2.getItems().add(music); 
        Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/avishkar","root","mtr281001");
         PreparedStatement stmt7=con.prepareStatement("insert into plsylist2 values(?)");
         stmt7.setString(1,music);
          stmt7.executeUpdate();
    }
    @FXML
    private void playlist3(ActionEvent event) throws ClassNotFoundException, SQLException{
       playlist3.getItems().add(music);
        Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/avishkar","root","mtr281001");
         PreparedStatement stmt7=con.prepareStatement("insert into plsylist3 values(?)");
         stmt7.setString(1,music);
          stmt7.executeUpdate();
    }
    @FXML
    private void playlist4(ActionEvent event) throws ClassNotFoundException, SQLException{
       playlist4.getItems().add(music);
        Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/avishkar","root","mtr281001");
         PreparedStatement stmt7=con.prepareStatement("insert into plsylist1 values(?)");
         stmt7.setString(1,music);
          stmt7.executeUpdate();
    }
    
    
    @FXML
    private void abc(ActionEvent event){
    JPanel p = new JPanel();
        JButton b1 = new JButton("kunal");
        playlist.getChildren().add(new Button("kunal"));
        }
    
    
    
    public void db(String k)
    {
        
    }
@Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //System.out.println("bhjb");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/avishkar","root","mtr281001");
            PreparedStatement stmt1=con.prepareStatement("select * from playlist1");
            ResultSet rs1 = stmt1.executeQuery();
            while(rs1.next())
                list4.add(rs1.getString(1));
            PreparedStatement stmt2=con.prepareStatement("select * from playlist2");
            ResultSet rs2 = stmt2.executeQuery();
            while(rs2.next())
                list5.add(rs2.getString(1));
            PreparedStatement stmt3=con.prepareStatement("select * from playlist3");
            ResultSet rs3 = stmt3.executeQuery();
            while(rs3.next())
                list6.add(rs1.getString(1));
            PreparedStatement stmt4=con.prepareStatement("select * from playlist4");
            ResultSet rs4 = stmt4.executeQuery();
            while(rs4.next())
                list7.add(rs1.getString(1));
            PreparedStatement stmt5=con.prepareStatement("select * from bookmarks");
            ResultSet rs5 = stmt5.executeQuery();
            while(rs5.next())
                list2.add(rs5.getString(1));
            PreparedStatement stmt6=con.prepareStatement("select * from recentlyplayed ORDER by no");
            ResultSet rs6 = stmt6.executeQuery();
            while(rs6.next())
                list3.add(rs1.getString(1));
            } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
            String maindirpath = "C:\\Users\\LENOVO-PC\\Desktop";
            File maindir = new File(maindirpath);
            if(maindir.exists() && maindir.isDirectory())
            {
                File arr[] = maindir.listFiles();
                RecursivePrint(arr,0,0);
            }
            
           
                ;
            
            
            
//System.out.println("jk");
bookmark.setItems(list2);
recentlyplayed.setItems(list3);
combobox.setItems(list1);
playlist1.setItems(list4);
playlist2.setItems(list5);
playlist3.setItems(list6);
playlist4.setItems(list7);
// playlist5.setItems(list8);


combobox.getItems().addAll(musicname);

//TODO
        
    }
    
}
