<?php
//Recibe datos
$Nom=$_REQUEST['nom'];
$Tipo=$_REQUEST['Tipo'];
$Valor=$_REQUEST['Valor'];
$img=$_REQUEST['img'];
$IdU=$_REQUEST['IdU'];

if(isset($Nom) && isset($Tipo) && isset($Valor) && isset($img) && isset($IdU)){
	$R['estado']="OK";
	try{
		$conn = new PDO("mysql:host=localhost;dbname=recetario", "root", "root");
		$conn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION); 
		$sql=$conn->prepare("CALL spInsReceta(:Nom, :Tipo, :Valor, :Img, :IdU)");
		$sql->execute(array('Nom'=>$Nom, 'Tipo'=>$Tipo, 'Valor'=>$Valor, 'Img'=>$img, 'IdU'=>$IdU));
				$conn=null;
		echo json_encode($R);
	}catch(PDOEXEPTION $e){
		$R['estado']="Error".$e->getMessage();
	}	
}else{
	$R['estado']="NO";
	echo json_encode($R);
}
?>