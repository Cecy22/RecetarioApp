<?php
//Recibe datos
$Nom=$_REQUEST['nom'];
$usu=$_REQUEST['usu'];
$Fnac=$_REQUEST['fnac'];
$pass=$_REQUEST['pass'];
$img=$_REQUEST['img'];

if (isset($Nom) && isset($usu) && isset($Fnac) && isset($pass) && isset($img)){
	$R['estado']="OK";
	try{
		$conn = new PDO("mysql:host=localhost;dbname=recetario", "root", "root");
		$conn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION); 
		$sql=$conn->prepare("CALL spInsUsuario(:Nom, :Usu, :Fnac, :Contra, :Img)");
		$sql->execute(array('Nom'=>$Nom, 'Usu'=>$usu, 'Fnac'=>$Fnac, 'Contra'=>$pass, 'Img'=>$img));
		$conn=null;
		echo json_encode($R);
	}catch(PDOEXEPTION $e){
		$R['estado']="Error".$e->getMessage();
		echo json_encode($R);
	}	
}else{
	$R['estado']="NO";
	echo json_encode($R);
}
?>