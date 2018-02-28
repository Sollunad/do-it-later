<?php 
if(!empty($_POST["db"])&&!empty($_POST["user"])&&!empty($_POST["pass"])&&!empty($_POST["query"])) { 
$db = new mysqli('rdbms.strato.de', $_POST["user"], $_POST["pass"], $_POST["db"]); 
 
if($db->connect_errno > 0){
            die('Aufbau einer Datenbankverbindung nicht möglich [' . $db->connect_error . ']');
        } else{
            $sql=$_POST["query"];
            if(!$result = $db->query($sql)){
                die('Fehler beim Ausführen der SQL-Abfrage [' . $db->error . ']');
            }else{
                while($res=mysqli_fetch_Assoc($result)){
                    $rows[]=$res;
                }
                print json_encode($rows);
            }
        }   
    }else{  
        echo "Verbindungsdaten unvollständig";
    }
?>