<?php
//Recibe datos
//in pnom varchar(80), pcant varchar(11), pUni varchar(70), pidr int
$Nom=$_REQUEST['nom'];

if(isset($Nom)){
	$R['estado']="OK";
	try{
		$conn = new PDO("mysql:host=localhost;dbname=recetario", "root", "root");
		$conn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION); 
		$sql=$conn->prepare("CALL spInsIngrediente(:Nom)");
		$sql->execute(array('Nom'=>$Nom));
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