
package sims;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton; 
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;



public class Editing1Controller implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {  
        Edit1.requestFocus();
        //IdFld.setStyle("-fx-border-color: #ff2323; -fx-text-fill: white; -fx-prompt-text-fill: white;"); when error occurs or not filled
                
        
        chzEduDrpDwn();
        chzDeptDrpDwn();
        chzDobMnth();
                
        NamFld.setTooltip(new Tooltip ("use Fullname"));
        DobYear.setTooltip(new Tooltip("eg. 1997"));
        SemFld.setTooltip(new Tooltip("verify Graduation level before input"));
    }    
    
    
    public static String a, useName;
    public static int up, useSem, iu = 0;
    
    
    @FXML
    private Label delData;
    
    @FXML
    private Pane Edit1;
    
    public static String s1, s2, s3, s4, s6, s7, s72;
    public static int s5, s71, s73;  
    
    @FXML
    private JFXTextField NamFld;
    public void getName()
    {       
        String l = NamFld.getText();
        if(l.matches("[a-zA-Z]+") == false || l.length() == 0)
        { 
            NamFld.setText(null);
            NamFld.setStyle("-fx-border-color: #ff2323; -fx-text-fill: white; -fx-prompt-text-fill: white;");
            System.out.println("false"); 
            s1 = null;
        } 
        else 
        { 
            NamFld.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white; -fx-prompt-text-fill: white;");
            s1 = "'"+l+"'";
            System.out.println(s1);
            useName = s1;
        }
        
        
    }
    
    @FXML
    public JFXTextField IdFld;
    public void getID()
    {   s2 = "'"+IdFld.getText()+"'";
        System.out.println(s2);
    }
    
    @FXML
    private MenuButton DeptDrpDwn;
         
    @FXML
    private MenuItem mech;

    @FXML
    private MenuItem cs;
    
    @FXML
    private MenuItem enc;

    @FXML
    private MenuItem civ;
    
    public void chzDeptDrpDwn()
    {
              
       mech.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                DeptDrpDwn.setText("MECH");
                String ab  = "MECH";
                s3 = "'"+ab+"'";
                System.out.println(s3);

            }
       } );
       
       cs.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                DeptDrpDwn.setText("CSE");
                String ab  = "CSE";
                s3 = "'"+ab+"'";   
                System.out.println(s3);
            }
       } );
       
       enc.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                DeptDrpDwn.setText("EnC");
                String ab  = "EnC";
                s3 = "'"+ab+"'";
                System.out.println(s3);
            }
       } );
       
       civ.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                DeptDrpDwn.setText("CIVIL");
                String ab  = "CIVIL";
                s3 = "'"+ab+"'";
                System.out.println(s3);
            }
       } );
    }

    @FXML
    private MenuButton EduDrpDwn;
 
    @FXML
    private MenuItem pg;
    
    @FXML
    private MenuItem ug;
    
    public void chzEduDrpDwn()
    {
              
       pg.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                EduDrpDwn.setText("Postgraduation");
                up = 1;
                String ab  = "PG";
                s4 = "'"+ab+"'";
                System.out.println(s4);
                useSem = 0;
                s5 = 0;
                SemFld.setText(null);
            
            }
       });
       
       ug.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                EduDrpDwn.setText("Undergraduation");
                up = 2;
                String ab  = "UG";
                s4 = "'"+ab+"'";
                System.out.println(s4);
                useSem = 0;
                s5 = 0;
                SemFld.setText(null);
                
            }
        });
    }
    
    @FXML
    private JFXTextField SemFld;
    public void getSem()
    {       
        int l = Integer.parseInt(SemFld.getText());
        if(up == 2 && l<=9 && l>0)
        {   
            SemFld.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white; -fx-prompt-text-fill: white;");
            s5 = l;
            useSem = s5;
            System.out.println(s5);
        }
        
        else if(up == 1 && l<=5 && l>0)
        {   
            SemFld.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white; -fx-prompt-text-fill: white;");
            s5 = l;
            useSem = s5;
            System.out.println(s5);
        } 
        
        else
        {
            SemFld.setText(null);
            SemFld.setStyle("-fx-border-color: #ff2323; -fx-text-fill: white; -fx-prompt-text-fill: white;");
            s5 = 0;
            System.out.println("check");
        }

    }
    
    @FXML
    private JFXTextField CntctFld;
    public void getCntct()
    {  
        String l = "'"+CntctFld.getText()+"'";
        
        if(l.length() == 12)
        {
            CntctFld.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white; -fx-prompt-text-fill: white;");
            s6 = l;
            System.out.println(s6);
            
        }
        else
        {
            System.out.println(l);
            CntctFld.setText(null);
            CntctFld.setStyle("-fx-border-color: #ff2323; -fx-text-fill: white; -fx-prompt-text-fill: white;");
            s6 = null;
            System.out.println("check");
            
        }
    } 
    
    @FXML
    private JFXTextField DobYear;
    public void DobYear()
    {      
        String l = DobYear.getText();
        if(l.length() == 4)
        {
            try
            {
                DobYear.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white; -fx-prompt-text-fill: white;");
                s71 = Integer.parseInt(DobYear.getText());
                System.out.println(s71);
            }
            catch(Exception e)
            {
                DobYear.setText(null);
                DobYear.setStyle("-fx-border-color: #ff2323; -fx-text-fill: white; -fx-prompt-text-fill: white;");
            }
        }
        else
        {
            DobYear.setText(null);
            DobYear.setStyle("-fx-border-color: #ff2323; -fx-text-fill: white; -fx-prompt-text-fill: white;");
        }
            
    }
    
    int dd;
    
    @FXML
    private Label MnthLbl;
    
    @FXML
    private MenuButton DobMnth;
    
    
    @FXML
    private MenuItem jan;
    
    @FXML
    private MenuItem fab;

    @FXML
    private MenuItem march; 

    @FXML
    private MenuItem april;
    
    @FXML
    private MenuItem may;

    @FXML
    private MenuItem june;

    @FXML
    private MenuItem july;
    
    @FXML
    private MenuItem aug;
    
    @FXML
    private MenuItem sept;
    
    @FXML
    private MenuItem oct;

    @FXML
    private MenuItem nov;
    
    @FXML
    private MenuItem dec;
    
    public void chzDobMnth()
    {
              
       jan.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 31;
                DobMnth.setText("January");
                MnthLbl.setVisible(true);
                s72 = "1";
            }
       }       );
       
       fab.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                if(s71%4 == 0)
                {
                    dd = 29;
                }
                else
                {
                    dd = 28;
                }
                DobMnth.setText("Fabruary");
                MnthLbl.setVisible(true);
                s72 = "2";
            }
       }       );
       
       march.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 31;
                DobMnth.setText("March");
                MnthLbl.setVisible(true);
                s72 = "3";
            }
       }
       );
       
       april.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 30;
                DobMnth.setText("April");
                MnthLbl.setVisible(true);
                s72 = "4";
            }
       }
       );
       
       may.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 31;
                DobMnth.setText("May");
                MnthLbl.setVisible(true);
                s72 = "5";
            }
       }
       );
       
       june.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 30;
                DobMnth.setText("June");
                MnthLbl.setVisible(true);
                s72 = "6";
            }
       }
       );
       
       july.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 31;
                DobMnth.setText("July");
                MnthLbl.setVisible(true);
                s72 = "7";
            }
       }
       );
       
       aug.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 31;
                DobMnth.setText("August");
                MnthLbl.setVisible(true);
                s72 = "8";
            }
       }
       );
       
       sept.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 30;
                DobMnth.setText("September");
                MnthLbl.setVisible(true);
                s72 = "9";
            }
       }
       );
       
       oct.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 31;
                DobMnth.setText("October");
                MnthLbl.setVisible(true);
                s72 = "10";
            }
       }
       );
       
       nov.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 30;
                DobMnth.setText("November");
                MnthLbl.setVisible(true);
                s72 = "11";
            }
       }
       );
       
       dec.setOnAction(new EventHandler<ActionEvent>() 
       {
            public void handle(ActionEvent t) 
            {
                dd = 31;
                DobMnth.setText("December");
                MnthLbl.setVisible(true);
                s72 = "12";
    
            }
       }
       );
       
    }
    
    @FXML
    private JFXTextField DobDay;
    public void DobDay()
    {  
        int l = Integer.parseInt(DobDay.getText());
        if(l <= dd)
        {
            DobDay.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white; -fx-prompt-text-fill: white;");
            s73 = l;
            System.out.println(s73);
            s7 = "'"+ s71 + "-" + s72 + "-" + s73 + "'"; 
            System.out.println(s7);
        }
        else
        {
            DobDay.setText(null);
            DobDay.setStyle("-fx-border-color: #ff2323; -fx-text-fill: white; -fx-prompt-text-fill: white;");
        }
    }
    
    
   
    public static void setValues()
    {
        DatabaseIO d = new DatabaseIO();
        d.setBasicinfo(s1, s2, s3, s4, s5, s6, s7); 
    }
    
    public void delEdit1()
    {
       DatabaseIO d = new DatabaseIO();
       d.DelBasicinfo(useName); 
    }
    @FXML
    private Label BtnTo2;
    
    public void toEdit2(MouseEvent event) //this function allows to transport to another tab without opening another window and same goes for other 2 functions
    {
        
        
        try {
           
            Parent editPag1 = FXMLLoader.load(getClass().getResource("Editing2.fxml"));
            Scene editPg1Scene = new Scene(editPag1);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            appStage.setScene(editPg1Scene);
            appStage.show();
            
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
 
    @FXML
    private Label BtnTo3;  
    
    public void toEdit3(MouseEvent event)
    {
        
        try 
        {
            Parent editPag1 = FXMLLoader.load(getClass().getResource("Editing3.fxml"));
            Scene editPg1Scene = new Scene(editPag1);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            appStage.setScene(editPg1Scene);
            appStage.show();
        } 
        catch (Exception ex) 
          {
            ex.printStackTrace();
          }
    }
    
    @FXML
    private Label bck;
    
    public void bck2HP(MouseEvent event)
    {
        try 
        {
            Parent editPag1 = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene editPg1Scene = new Scene(editPag1);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            appStage.setScene(editPg1Scene);
            appStage.show();
        } 
        catch (Exception ex) 
          {
            ex.printStackTrace();
          }
    }

}